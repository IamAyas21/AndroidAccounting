package com.stratone.accounting.adapter;
import com.stratone.accounting.model.CashFlowDetail;
import com.stratone.accounting.model.CashFlowHeader;
import com.stratone.accounting.response.ResponseCashFlow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CashFlowDetailAdapter{
    public static HashMap<CashFlowHeader, List<CashFlowDetail>> getData(List<CashFlowHeader> cashFlowHeaders) {
        HashMap<CashFlowHeader, List<CashFlowDetail>> expandableListDetail = new HashMap<CashFlowHeader, List<CashFlowDetail>>();
        List<CashFlowDetail> cashFlowDetails;
        for (int i = 0;i < cashFlowHeaders.size();i++) {
            cashFlowDetails = cashFlowHeaders.get(i).getData();
            expandableListDetail.put(cashFlowHeaders.get(i),cashFlowDetails);
        }
        return expandableListDetail;
    }
}
