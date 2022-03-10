package com.person.shop_data_schedule;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.person.shop_data_schedule.data.MemberHashDataVO;
import com.person.shop_data_schedule.data.MemberInfoVO;
import com.person.shop_data_schedule.data.MemberProdVO;
import com.person.shop_data_schedule.data.PageConnVO;
import com.person.shop_data_schedule.data.ProductHashDataVO;
import com.person.shop_data_schedule.data.ProductInfoVO;
import com.person.shop_data_schedule.data.RecommendProdToMemberVO;
import com.person.shop_data_schedule.data.ReviewInfoVO;
import com.person.shop_data_schedule.data.ShoppingRecordVO;
import com.person.shop_data_schedule.mapper.HistoryMapper;
import com.person.shop_data_schedule.mapper.RecommendMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class APIController {
    @Value("${history.path}")String path;
    @Value("${history.conn.path}")String conn_path;
    @Value("${history.prod.path}")String prod_path;
    @Value("${history.review.path}")String review_path;
    @Value("${history.shopping_cart.path}")String shopping_cart_path;
    @Value("${history.shopping_buy.path}")String shopping_buy_path;
    @Value("${history.shopping_cancel.path}")String shopping_cancel_path;
    @Value("${history.member_info.path}")String member_path;
    @Value("${history.product_info.path}")String product_path;
    @Value("${recommend.hash.path}")String recommend_hash_path;
    @Value("${recommend.output.path}")String recommend_output_path;
    @Autowired HistoryMapper history_mapper;
    @Autowired RecommendMapper recommend_mapper;

    @GetMapping("/conn")
    public String userConnData(@RequestParam String start, @RequestParam String end) throws Exception{
        Calendar cal = Calendar.getInstance();
        cal.set(
            Integer.parseInt(start.split("-")[0]), 
            Integer.parseInt(start.split("-")[1])-1, 
            Integer.parseInt(start.split("-")[2]),
            0,0,0
        );
        Date startDt = cal.getTime();

        cal.set(
            Integer.parseInt(end.split("-")[0]), 
            Integer.parseInt(end.split("-")[1])-1, 
            Integer.parseInt(end.split("-")[2]),
            23,59,59
        );
        Date endDt = cal.getTime();

        List<PageConnVO> list = history_mapper.selectPageConnHistory(startDt, endDt);
        Calendar c = Calendar.getInstance();
        String src = path+"connect"+c.getTimeInMillis()+".txt";
        BufferedWriter writer = new BufferedWriter(
            new FileWriter(
                new File(src)
            )
        );
        for(PageConnVO data:list){
            writer.write(data.toString());
            writer.newLine();
        }
        writer.close();

        String dest = conn_path+"connect"+c.getTimeInMillis()+".txt";
        File srcFile = new File(src);
        File destFile = new File(dest);
        srcFile.renameTo(destFile);

        return "페이지 접속 정보 수집 스케줄 실행";
    }
    
    @GetMapping("/prod")
    public String memberProdData(@RequestParam String start, @RequestParam String end)throws Exception{
        Calendar c = Calendar.getInstance();
        String src = path+"member_prod"+c.getTimeInMillis()+".txt";
        String dest = prod_path+"member_prod"+c.getTimeInMillis()+".txt";
        // String src = path+"member_prod["+c.getTimeInMillis()+"].txt";
        // String dest = prod_path+"member_prod["+c.getTimeInMillis()+"].txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(src)));

        Calendar cal = Calendar.getInstance();
        cal.set(
            Integer.parseInt(start.split("-")[0]), 
            Integer.parseInt(start.split("-")[1])-1, 
            Integer.parseInt(start.split("-")[2]),
            0,0,0
        );
        Date startDt = cal.getTime();

        cal.set(
            Integer.parseInt(end.split("-")[0]), 
            Integer.parseInt(end.split("-")[1])-1, 
            Integer.parseInt(end.split("-")[2]),
            23,59,59
        );
        Date endDt = cal.getTime();

        List<MemberProdVO> list = history_mapper.selectMemberProdHistory(startDt, endDt);
        for(MemberProdVO x:list){
            bw.write(x.toString());
            bw.newLine();
        }
        bw.close();

        File srcFile = new File(src);
        File destFile = new File(dest);

        srcFile.renameTo(destFile);
        return "제품 조회 정보 수집 스케줄 실행";
    }
    
    @GetMapping("/review")
    public String reviewData(@RequestParam String start, @RequestParam String end)throws Exception{
        Calendar c = Calendar.getInstance();
        String src = path+"review"+c.getTimeInMillis()+".txt";
        String dest = review_path+"review"+c.getTimeInMillis()+".txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(src)));

        Calendar cal = Calendar.getInstance();
        cal.set(
            Integer.parseInt(start.split("-")[0]), 
            Integer.parseInt(start.split("-")[1])-1, 
            Integer.parseInt(start.split("-")[2]),
            0,0,0
        );
        Date startDt = cal.getTime();

        cal.set(
            Integer.parseInt(end.split("-")[0]), 
            Integer.parseInt(end.split("-")[1])-1, 
            Integer.parseInt(end.split("-")[2]),
            23,59,59
        );
        Date endDt = cal.getTime();

        List<ReviewInfoVO> list = history_mapper.selectReviewInfo(startDt, endDt);
        for(ReviewInfoVO x:list){
            bw.write(x.toString());
            bw.newLine();
        }
        bw.close();

        File srcFile = new File(src);
        File destFile = new File(dest);

        srcFile.renameTo(destFile);
        return "리뷰 정보 수집 스케줄 실행";
    }
    
    @GetMapping("/shopping")
    public String shoppingData(@RequestParam String start, @RequestParam String end)throws Exception{
        Calendar c = Calendar.getInstance();
        String src_cart = path+"shopping_cart"+c.getTimeInMillis()+".txt";
        String src_buy = path+"shopping_buy"+c.getTimeInMillis()+".txt";
        String src_cancel = path+"shopping_cancel"+c.getTimeInMillis()+".txt";
        String dest_cart = shopping_cart_path+"shopping_cart"+c.getTimeInMillis()+".txt";
        String dest_buy = shopping_buy_path+"shopping_buy"+c.getTimeInMillis()+".txt";
        String dest_cancel = shopping_cancel_path+"shopping_cancel"+c.getTimeInMillis()+".txt";

        BufferedWriter bw_cart = new BufferedWriter(new FileWriter(new File(src_cart)));
        BufferedWriter bw_buy = new BufferedWriter(new FileWriter(new File(src_buy)));
        BufferedWriter bw_cancel = new BufferedWriter(new FileWriter(new File(src_cancel)));

        Calendar cal = Calendar.getInstance();
        cal.set(
            Integer.parseInt(start.split("-")[0]), 
            Integer.parseInt(start.split("-")[1])-1, 
            Integer.parseInt(start.split("-")[2]),
            0,0,0
        );
        Date startDt = cal.getTime();

        cal.set(
            Integer.parseInt(end.split("-")[0]), 
            Integer.parseInt(end.split("-")[1])-1, 
            Integer.parseInt(end.split("-")[2]),
            23,59,59
        );
        Date endDt = cal.getTime();

        List<ShoppingRecordVO> list_cart = history_mapper.selectShoppingRecord(startDt, endDt, 0);
        List<ShoppingRecordVO> list_buy = history_mapper.selectShoppingRecord(startDt, endDt, 1);
        List<ShoppingRecordVO> list_cancel = history_mapper.selectShoppingRecord(startDt, endDt, 2);

        for(ShoppingRecordVO x:list_cart){
            bw_cart.write(x.toString());
            bw_cart.newLine();
        }
        for(ShoppingRecordVO x:list_buy){
            bw_buy.write(x.toString());
            bw_buy.newLine();
        }
        for(ShoppingRecordVO x:list_cancel){
            bw_cancel.write(x.toString());
            bw_cancel.newLine();
        }
        bw_cart.close();
        bw_buy.close();
        bw_cancel.close();

        File src_cart_File = new File(src_cart);
        File src_buy_File = new File(src_buy);
        File src_cancel_File = new File(src_cancel);
        File dest_cart_File = new File(dest_cart);
        File dest_buy_File = new File(dest_buy);
        File dest_cancel_File = new File(dest_cancel);

        src_cart_File.renameTo(dest_cart_File);
        src_buy_File.renameTo(dest_buy_File);
        src_cancel_File.renameTo(dest_cancel_File);
        return "쇼핑 정보 수집 스케줄 실행";
    }

    @GetMapping("/member")
    public String getMemberInfo(@RequestParam String start, @RequestParam String end)throws Exception{
        Calendar c = Calendar.getInstance();
        String src = path+"member"+c.getTimeInMillis()+".txt";
        String dest = member_path+"member"+c.getTimeInMillis()+".txt";

        Calendar cal = Calendar.getInstance();
        cal.set(
            Integer.parseInt(start.split("-")[0]), 
            Integer.parseInt(start.split("-")[1])-1, 
            Integer.parseInt(start.split("-")[2]),
            0,0,0
        );
        Date startDt = cal.getTime();

        cal.set(
            Integer.parseInt(end.split("-")[0]), 
            Integer.parseInt(end.split("-")[1])-1, 
            Integer.parseInt(end.split("-")[2]),
            23,59,59
        );
        Date endDt = cal.getTime();

        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(src)));

        List<MemberInfoVO> list = history_mapper.selectMemberInfo(startDt, endDt);
        for(MemberInfoVO x:list){
            bw.write(x.toString());
            bw.newLine();
        }
        bw.close();

        File src_file = new File(src);
        File dest_file = new File(dest);
        src_file.renameTo(dest_file);

        return "회원정보 수집 스케줄 실행";
    }
    @GetMapping("/product")
    public String getProductInfo(@RequestParam String start, @RequestParam String end)throws Exception{
        Calendar c = Calendar.getInstance();
        String src = path+"product"+c.getTimeInMillis()+".txt";
        String dest = product_path+"product"+c.getTimeInMillis()+".txt";

        Calendar cal = Calendar.getInstance();
        cal.set(
            Integer.parseInt(start.split("-")[0]), 
            Integer.parseInt(start.split("-")[1])-1, 
            Integer.parseInt(start.split("-")[2]),
            0,0,0
        );
        Date startDt = cal.getTime();

        cal.set(
            Integer.parseInt(end.split("-")[0]), 
            Integer.parseInt(end.split("-")[1])-1, 
            Integer.parseInt(end.split("-")[2]),
            23,59,59
        );
        Date endDt = cal.getTime();
        
        List<ProductInfoVO> list = history_mapper.selectProductInfo(startDt, endDt);
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(src)));

        for(ProductInfoVO x:list){
            bw.write(x.toString());
            bw.newLine();
        }
        bw.close();

        File src_file = new File(src);
        File dest_file = new File(dest);

        src_file.renameTo(dest_file);
        return "제품정보 수집 스케줄 실행";
    }

    @GetMapping("/recommend")
    public String getRecommendData()throws Exception{
        File member_hash_file = new File(recommend_hash_path+"member/000000_0");
        File product_hash_file = new File(recommend_hash_path+"product/000000_0");
        File recommend_file = new File(recommend_output_path+"part-r-00000");
        List<MemberHashDataVO> member_list = new ArrayList<MemberHashDataVO>();
        List<ProductHashDataVO> product_list = new ArrayList<ProductHashDataVO>();
        List<RecommendProdToMemberVO> recommend_list = new ArrayList<RecommendProdToMemberVO>();

        BufferedReader reader = new BufferedReader(new FileReader(member_hash_file));
        String line = null;
        while((line = reader.readLine()) != null){
            MemberHashDataVO data = new MemberHashDataVO();
            data.setMhd_hash(line.split("[|]")[0]);
            data.setMhd_email(line.split("[|]")[1]);
            try{
                data.setMhd_mi_seq(Integer.parseInt(line.split("[|]")[2]));
            }catch(NumberFormatException e){
                continue;
            }
            member_list.add(data);
        }
        reader.close();

        reader = new BufferedReader(new FileReader(product_hash_file));
        line = null;
        while((line = reader.readLine()) != null){
            ProductHashDataVO data = new ProductHashDataVO();
            data.setPhd_hash(line.split("[|]")[0]);
            data.setPhd_name(line.split("[|]")[1]);
            try{
                data.setPhd_pi_seq(Integer.parseInt(line.split("[|]")[2]));
            }catch(NumberFormatException e){
                continue;
            }
            product_list.add(data);
        }
        reader.close();


        reader = new BufferedReader(new FileReader(recommend_file));
        line = null;
        while((line = reader.readLine()) != null){
            String member_hash = line.split("\\[")[0].trim();
            String[] product = line.split("\\[")[1].replaceAll("\\]", "").split(",");
            for(int i=0; i<product.length; i++){
                RecommendProdToMemberVO data = new RecommendProdToMemberVO();
                data.setRptm_member_hash(member_hash);
                data.setRptm_product_hash(product[i].split(":")[0]);
                data.setRptm_score(Double.parseDouble(product[i].split(":")[1]));
                recommend_list.add(data);
            }
        }
        reader.close();
        recommend_mapper.insertMemberHashData(member_list);
        recommend_mapper.insertProductHashData(product_list);
        recommend_mapper.insertRecommendHashData(recommend_list);
        return "추천 정보를 가져왔습니다.";
    }
}
