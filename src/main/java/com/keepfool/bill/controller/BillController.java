package com.keepfool.bill.controller;

import com.keepfool.bill.bean.Bill;
import com.keepfool.bill.service.BillService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class BillController {
    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @ResponseBody
    @PostMapping("/bill")
    public int bookkeeping(@RequestBody Bill bill) {
        return billService.bookkeeping(bill);
    }

    @ResponseBody
    @GetMapping("/bill/{userId}/{billTime}")
    public Map<String, Object> getBillDetails(@PathVariable("userId") int userId, @PathVariable("billTime") Date billTime) {
        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(dateFormat.format(billTime));
        map.put("amount", billService.getCategoryStateAmount(userId, dateFormat.format(billTime)));
        map.put("list", billService.getBillDetails(userId, dateFormat.format(billTime)));
        return map;
    }
}
