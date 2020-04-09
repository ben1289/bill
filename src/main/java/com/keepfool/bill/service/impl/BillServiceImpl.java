package com.keepfool.bill.service.impl;

import com.keepfool.bill.bean.Bill;
import com.keepfool.bill.mapper.BillMapper;
import com.keepfool.bill.service.BillService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BillServiceImpl implements BillService {
    final BillMapper billMapper;

    public BillServiceImpl(BillMapper billMapper) {
        this.billMapper = billMapper;
    }

    @Override
    public int bookkeeping(Bill bill) {
        billMapper.bookkeeping(bill);
        return bill.getBillId();
    }

    @Override
    public int modifyBills(Bill bill) {
        billMapper.modifyBills(bill);
        return bill.getBillId();
    }

    @Override
    public List<Map<String, Object>> getBillDetails(int userId, String billTime) {
        List<Bill> billCategoryAmount = billMapper.getBillCategoryAmount(userId, billTime);
        List<Bill> billDetails = billMapper.getBillDetails(userId, billTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<Map<String, Object>> lists = new ArrayList<>();

        billCategoryAmount.forEach(item -> {
            Map<String, Object> map = new HashMap<>();
            map.put("date", dateFormat.format(item.getBillTime()));
            map.put("income", item.getCategory().getCategoryState() == 1 ? item.getBillAmount() : 0);
            map.put("expend", item.getCategory().getCategoryState() == 0 ? item.getBillAmount() : 0);

            List<Map<String, Object>> list = new ArrayList<>();
            billDetails.forEach(detail -> {
                if (detail.getBillTime().equals(item.getBillTime())) {
                    Map<String, Object> detailsMap = new HashMap<>();
                    detailsMap.put("billId", detail.getBillId());
                    detailsMap.put("icon", detail.getCategory().getCategoryIcon());
                    detailsMap.put("remark", detail.getBillRemark());
                    detailsMap.put("money", detail.getBillAmount());
                    list.add(detailsMap);
                }
            });
            map.put("details", list);
            lists.add(map);
        });
        return lists;
    }

    @Override
    public Map<String, Integer> getCategoryStateAmount(int userId, String billTime) {
        List<Bill> categoryStateAmount = billMapper.getCategoryStateAmount(userId, billTime);
        Map<String, Integer> map = new HashMap<>();
        categoryStateAmount.forEach(item -> {
            String state = item.getCategory().getCategoryState() == 0 ? "expend" : "income";
            map.put(state, item.getBillAmount());
        });
        return map;
    }

    @Override
    public Bill getBillDetailByBillId(int billId) {
        return billMapper.getBillDetailByBillId(billId);
    }
}
