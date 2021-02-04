package com.stratone.accounting.adapter;

import com.stratone.accounting.model.CashFlowDetail;
import com.stratone.accounting.model.CashFlowHeader;
import com.stratone.accounting.model.LedgerAccountModel;
import com.stratone.accounting.model.LedgerDataModel;
import com.stratone.accounting.model.LedgerModel;

import java.util.HashMap;
import java.util.List;

public class LedgerDetailAdapter {
    public static HashMap<LedgerAccountModel, List<LedgerModel>> getData(List<LedgerAccountModel> ledgerAccountModels) {
        HashMap<LedgerAccountModel, List<LedgerModel>> expandableListDetail = new HashMap<LedgerAccountModel, List<LedgerModel>>();
        List<LedgerModel> ledgerDataModels;
        for (int i = 0;i < ledgerAccountModels.size();i++) {
            ledgerDataModels = ledgerAccountModels.get(i).getMutations();
            expandableListDetail.put(ledgerAccountModels.get(i),ledgerDataModels);
        }
        return expandableListDetail;
    }
}
