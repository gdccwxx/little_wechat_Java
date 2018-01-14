package com.lendbook.wechat_program.controller;

import antlr.StringUtils;
import com.lendbook.wechat_program.Tools.GetPlaceByIp;
import com.lendbook.wechat_program.Tools.TellBookExitOrNot;
import com.lendbook.wechat_program.component.BookProperties;
import com.lendbook.wechat_program.model.*;
import com.lendbook.wechat_program.repository.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class AdminOperator {
    final long dayTimeStramp = 24 * 60 * 100;
    final long date = 3 * 365 * dayTimeStramp;                  // 3 years of timestramp
    @Autowired
    private BookProperties bookProperties;
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private BookTagRepo bookTagRepo;
    @Autowired
    private LendBookRepo lendBookRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private OperatorRepo operatorRepo;
    @ResponseBody


    @PostMapping(value = "/admin/addbook")
    public Map<String, String> adminAddBook (@RequestBody String m){
        Map<String, String> map = new HashMap<>();
        Map<String, String>matt = parsingString(m);
        String isbn = matt.get("isbn");
        String count = matt.get("count");
        String url;
        GetPlaceByIp getPlaceByIp = new GetPlaceByIp();
        Book book = new Book();
        if (isbn.length() != 13 || count.equals("")){
            map.put("msg", "you have wrong input!");
            return  map;
        }
        if (bookRepo.findByIsbn13(isbn) != null){
            map.put("msg", "database have this book");
            return map;
        }
        url =  bookProperties.getIsbnUrl() + isbn;
        String str = getPlaceByIp.getResponse(url);
        if (str.equals("")){
            map.put("msg", "none of this book in douban");
            return map;
        }
        JSONObject json = null;
        try {
            json = new JSONObject(str);
            book.setRating((float) json.getJSONObject("rating").getDouble("average"));
            String author=json.get("author").toString();
            author=StringUtils.stripFrontBack(author,"[","]");

            author=author.replace("\"","");

            book.setAuthor( author);

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

            for (int i = 0; i < json.getJSONArray("tags").length(); i++){

                BookTag bookTag = new BookTag();

                bookTag.setCount(Integer.parseInt(json.getJSONArray("tags").getJSONObject(i).get("count").toString()));

                bookTag.setName(json.getJSONArray("tags").getJSONObject(i).get("title").toString());

                bookTag.setIsbn(book.getIsbn13());

                bookTagRepo.save(bookTag);

            }

            bookRepo.save(book);

            map.put("msg", "add book successful");

        }catch (JSONException e){

            map.put("msg", "something wrong in server");

        }
        System.out.println(map.toString());
        return map;

    }
    @PostMapping(value = "/admin/returnbook")
    public Map<String, String> returnBook(@RequestBody String m) {
        Map<String, String> map = new HashMap<>();
        Map<String, String>matt = parsingString(m);
        String wechat = matt.get("wechat");
        String isbn = matt.get("isbn");
        LendBook lendBook = lendBookRepo.WetherLendBook(wechat,isbn);
        if (lendBook == null){
            map.put("msg", "none of this book");
        }else {
            Book book = bookRepo.findByIsbn13(isbn);

            User user = userRepo.findByWechat(wechat);
            book.setStoreNum(book.getStoreNum() + 1);
            book.setLendNum(book.getLendNum() - 1);
            Calendar now = Calendar.getInstance();
            if(now.getTimeInMillis()>lendBook.getReturnTime().getTimeInMillis()){
                long day = (now.getTimeInMillis()-lendBook.getReturnTime().getTimeInMillis()) / dayTimeStramp;
                user.setMoney((float) (user.getMoney()- (day * 0.01 * Float.parseFloat(book.getPrice()))));                    // kou qian
                userRepo.save(user);
            }
            lendBook.setDistincReturn(true);
            lendBook.setReturnTime(now);
            lendBook.setDistincHistory(true);
//            TellBookExitOrNot tellBookExitOrNot = new TellBookExitOrNot();
//            tellBookExitOrNot.tellBookExit(isbn);
            map.put("msg", "return succfully");
        }
        return map;
    }

    //用户充值
    @PostMapping(value = "/user/recharge")
    public Map<String,String> userRecharge (@RequestBody String m)
    {

        Map<String,String> res = new HashMap<String, String>();
        Map<String, String>matt = parsingString(m);
        String wechat = matt.get("wechat");
        Float money = Float.parseFloat(matt.get("money"));
        User user = userRepo.findByWechat(wechat);
        if (user ==null)
        {
            res.put("result","未找到");
        }
        else {
           user.setMoney(user.getMoney()+money);
           userRepo.save(user);
           res.put("result","充值成功!");
        }
        return  res;
    }
    //登录
    @GetMapping(value = "/admin/login/{account}")
    public String  userLogin(@PathVariable("account")String account)
    {
        if (account == operatorRepo.findOne(account).getAccount().toString())
        {
            return operatorRepo.findOne(account).getPassword();
        }
        return "";
    }
    private Map<String,String> parsingString(String str){
        Map<String , String> map = new HashMap<>();

        String [] strs = str.split("%7B|%2C|%3D|%7D|=|\\+");
        map.put(strs[1], strs[2]);
        map.put(strs[4], strs[5]);
        return map;
    }
}
