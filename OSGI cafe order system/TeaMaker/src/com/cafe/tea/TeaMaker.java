package com.cafe.tea;

import com.cafe.api.TeaService;

public class TeaMaker implements TeaService {
    @Override
    public void makeTea(String customerName , String flavor) {
        System.out.println(" TEA MAKER =  Made " + flavor + " tea for " + customerName);
    }
}

