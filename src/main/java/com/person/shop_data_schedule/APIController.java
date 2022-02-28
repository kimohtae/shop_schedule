package com.person.shop_data_schedule;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.person.shop_data_schedule.data.PageConnVO;
import com.person.shop_data_schedule.mapper.HistoryMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class APIController {
    @Value("${history.path}")String path;
    @Value("${history.conn.path}")String conn_path;
    @Autowired HistoryMapper history_mapper;
    
    @GetMapping("/history/conn")
    public String getPageConnHistory()throws Exception{
        Calendar search_dt = Calendar.getInstance();
        search_dt.set(Calendar.HOUR, 0);
        search_dt.set(Calendar.MINUTE, 0);
        search_dt.set(Calendar.SECOND, 0);
        
        Date endDt = search_dt.getTime();
        search_dt.add(Calendar.DATE, -1);
        Date startDt = search_dt.getTime();

        System.out.println(startDt);
        System.out.println(endDt);

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

        return "페이지 접속 히스토리를 내보냈습니다.";
    }
}
