package com.github.huda0209.arrowdespawntimer.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class IndexSelection {
    public static List<String> select(String arg, List<String> list){
        if(arg == null) return list;
        if(list.isEmpty()) return new ArrayList<String>();
        List<String> tabList = new ArrayList<String>();

        for(String content : list){
            if(content.toLowerCase(Locale.ROOT).startsWith(arg.toLowerCase(Locale.ROOT)))
                tabList.add(content);
        }

        return tabList;
    }
}
