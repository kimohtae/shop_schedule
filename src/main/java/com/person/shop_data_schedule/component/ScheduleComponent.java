package com.person.shop_data_schedule.component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.person.shop_data_schedule.data.MemberInfoVO;
import com.person.shop_data_schedule.data.MemberProdVO;
import com.person.shop_data_schedule.data.PageConnVO;
import com.person.shop_data_schedule.data.ProductInfoVO;
import com.person.shop_data_schedule.data.ReviewInfoVO;
import com.person.shop_data_schedule.data.ShoppingRecordVO;
import com.person.shop_data_schedule.mapper.HistoryMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleComponent{
    @Value("${history.path}")String path;
    @Value("${history.conn.path}")String conn_path;
    @Value("${history.prod.path}")String prod_path;
    @Value("${history.review.path}")String review_path;
    @Value("${history.shopping_cart.path}")String shopping_cart_path;
    @Value("${history.shopping_buy.path}")String shopping_buy_path;
    @Value("${history.shopping_cancel.path}")String shopping_cancel_path;
    @Value("${history.member_info.path}")String member_path;
    @Value("${history.product_info.path}")String product_path;
    @Autowired HistoryMapper history_mapper;

    @Scheduled(cron = "0 0 3 * * *")
    public void userConnDataSchedule() throws Exception{
        Calendar search_dt = Calendar.getInstance();
        search_dt.set(Calendar.HOUR, 0);
        search_dt.set(Calendar.MINUTE, 0);
        search_dt.set(Calendar.SECOND, 0);
        
        Date endDt = search_dt.getTime();
        search_dt.add(Calendar.DATE, -1);
        Date startDt = search_dt.getTime();


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
    }

    @Scheduled(cron = "0 0 3 * * *")
    public void memberProdDataSchedule()throws Exception{
        Calendar c = Calendar.getInstance();
        String src = path+"member_prod"+c.getTimeInMillis()+".txt";
        String dest = prod_path+"member_prod"+c.getTimeInMillis()+".txt";
        // String src = path+"member_prod["+c.getTimeInMillis()+"].txt";
        // String dest = prod_path+"member_prod["+c.getTimeInMillis()+"].txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(src)));

        Calendar search_dt = Calendar.getInstance();
        search_dt.set(Calendar.HOUR, 0);
        search_dt.set(Calendar.MINUTE, 0);
        search_dt.set(Calendar.SECOND, 0);

        Date end_dt = search_dt.getTime();
        search_dt.add(Calendar.DATE, -1);
        Date start_dt = search_dt.getTime();

        List<MemberProdVO> list = history_mapper.selectMemberProdHistory(start_dt, end_dt);
        for(MemberProdVO x:list){
            bw.write(x.toString());
            bw.newLine();
        }
        bw.close();

        File srcFile = new File(src);
        File destFile = new File(dest);

        srcFile.renameTo(destFile);
    }
    @Scheduled(cron = "0 10 3 * * *")
    public void reviewDataSchedule()throws Exception{
        Calendar c = Calendar.getInstance();
        String src = path+"review"+c.getTimeInMillis()+".txt";
        String dest = review_path+"review"+c.getTimeInMillis()+".txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(src)));

        Calendar search_dt = Calendar.getInstance();
        search_dt.set(Calendar.HOUR, 0);
        search_dt.set(Calendar.MINUTE, 0);
        search_dt.set(Calendar.SECOND, 0);

        Date end_dt = search_dt.getTime();
        search_dt.add(Calendar.DATE, -1);
        Date start_dt = search_dt.getTime();

        List<ReviewInfoVO> list = history_mapper.selectReviewInfo(start_dt, end_dt);
        for(ReviewInfoVO x:list){
            bw.write(x.toString());
            bw.newLine();
        }
        bw.close();

        File srcFile = new File(src);
        File destFile = new File(dest);

        srcFile.renameTo(destFile);
    }
    
    @Scheduled(cron = "0 20 3 * * *")
    public void shoppingDataSchedule()throws Exception{
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

        Calendar search_dt = Calendar.getInstance();
        search_dt.set(Calendar.HOUR, 0);
        search_dt.set(Calendar.MINUTE, 0);
        search_dt.set(Calendar.SECOND, 0);

        Date end_dt = search_dt.getTime();
        search_dt.add(Calendar.DATE, -1);
        Date start_dt = search_dt.getTime();

        List<ShoppingRecordVO> list_cart = history_mapper.selectShoppingRecord(start_dt, end_dt, 0);
        List<ShoppingRecordVO> list_buy = history_mapper.selectShoppingRecord(start_dt, end_dt, 1);
        List<ShoppingRecordVO> list_cancel = history_mapper.selectShoppingRecord(start_dt, end_dt, 2);

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
    }

    @Scheduled(cron = "0 30 3 * * *")
    public void MemberDataSchedule()throws Exception{
        Calendar c = Calendar.getInstance();
        String src = path+"member"+c.getTimeInMillis()+".txt";
        String dest = member_path+"member"+c.getTimeInMillis()+".txt";

        Calendar search_dt = Calendar.getInstance();
        search_dt.set(Calendar.HOUR, 0);
        search_dt.set(Calendar.MINUTE, 0);
        search_dt.set(Calendar.SECOND, 0);

        Date endDt = search_dt.getTime();
        search_dt.add(Calendar.DATE, -1);
        Date startDt = search_dt.getTime();

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

    }
    @Scheduled(cron = "0 35 3 * * *")
    public void ProductDataSchedule()throws Exception{
        Calendar c = Calendar.getInstance();
        String src = path+"product"+c.getTimeInMillis()+".txt";
        String dest = product_path+"product"+c.getTimeInMillis()+".txt";

        Calendar search_dt = Calendar.getInstance();
        search_dt.set(Calendar.HOUR, 0);
        search_dt.set(Calendar.MINUTE, 0);
        search_dt.set(Calendar.SECOND, 0);

        Date endDt = search_dt.getTime();
        search_dt.add(Calendar.DATE, -1);
        Date startDt = search_dt.getTime();
        
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
    }
}
