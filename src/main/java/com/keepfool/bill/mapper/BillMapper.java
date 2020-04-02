package com.keepfool.bill.mapper;

import com.keepfool.bill.bean.Bill;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BillMapper {
    int bookkeeping(Bill bill);
    List<Bill> getBillDetails(int userId, String billTime);
    List<Bill> getBillCategoryAmount(int userId, String billTime);
    List<Bill> getCategoryStateAmount(int userId, String billTime);
}
