package com.lendbook.wechat_program.controller;

import antlr.StringUtils;
import com.lendbook.wechat_program.Tools.GetPlaceByIp;
import com.lendbook.wechat_program.component.BookProperties;
import com.lendbook.wechat_program.model.Book;
import com.lendbook.wechat_program.model.BookTag;
import com.lendbook.wechat_program.repository.BookRepo;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class AdminOperator {
    final long date = 3 * 365 * 24 * 60 * 100;                  // 3 years of timestramp
    @Autowired
    private BookProperties bookProperties;
    @Autowired
    private BookRepo bookRepo;
    @ResponseBody
    @PostMapping(value = "/admin/addbook/")
    public Map<String, String> adminAddBook (@RequestParam(value = "isbn",required = true) String isbn, @RequestParam(value = "count",required = true) String count){
        Map<String, String> map = new HashMap<>();
        String url;
        GetPlaceByIp getPlaceByIp = new GetPlaceByIp();
        Book book = new Book();
        if (isbn.length() != 13 || count.equals("")){
            map.put("status", "you have wrong input!");
            return  map;
        }
        if (bookRepo.findByIsbn13(isbn) != null){
            map.put("status", "database have this book");
            return map;
        }
        url =  bookProperties.getIsbnUrl() + isbn;
        String str = getPlaceByIp.getResponse(url);
        if (str.equals("")){
            map.put("status", "none of this book in douban");
            return map;
        }
        JSONObject json = null;
        try {
            json = new JSONObject(str);
            book.setRating((float) json.getJSONObject("rating").getDouble("average"));
            String author=json.get("author").toString();
            author=StringUtils.stripFrontBack(author,"[","]");
            author=author.replace("\"","");
            book.setAuthor( new ArrayList(Arrays.asList(author.split( "; "))));
            book.setCatalog(json.getString("catalog").trim());
            book.setImgUrl(json.getString("images"));
            book.setIsbn13(json.getString("isbn13"));
            book.setIsbn10(json.getString("isbn10"));
            book.setNumRater(json.getJSONObject("rating").getInt("numRaters"));
            book.setLendNum(0);
            book.setPrice(json.getString("price"));
            book.setSummary(json.getString("summary").trim());
            book.setStoreNum(Integer.parseInt(count));
            book.setPublishDate(json.getString("pubdate"));
            book.setPublisher(json.getString("publisher"));
            book.setTitle(json.getString("title"));
            Calendar calendar = Calendar.getInstance();
            long timeStramp = calendar.getTimeInMillis();
            calendar.set(Integer.parseInt(json.getString("pubdate").split("-")[0]), Integer.parseInt(json.getString("pubdate").split("-")[1]), 1);
            if((timeStramp - calendar.getTimeInMillis()) > date) {
                book.setDistincOldOrNew(true);

            }else{
                book.setDistincOldOrNew(false);
            }
            BookTag tags = new BookTag();
            ArrayList<BookTag> arrTags = new ArrayList<>();
            for (int i = 0; i < json.getJSONArray("tags").length(); i++){
                tags.setCount(Integer.parseInt(json.getJSONArray("tags").getJSONObject(i).get("count").toString()));
                tags.setName(json.getJSONArray("tags").getJSONObject(i).get("title").toString());
                arrTags.add(i, tags);
            }
            bookRepo.save(book);
            map.put("status", "add book successful");

        }catch (JSONException e){
            map.put("status", "something wrong in server");
        }
        return map;
    }
}
