package com.keepfool.bill.service;

import com.keepfool.bill.bean.Bill;

import java.util.List;
import java.util.Map;

public interface BillService {
    int bookkeeping(Bill bill);
    List<Map<String, Object>> getBillDetails(int userId, String billTime);
    Map<String, Integer> getCategoryStateAmount(int userId, String billTime);
}
