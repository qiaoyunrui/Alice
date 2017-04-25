package com.juhezi.alice.util;

import java.util.List;

/**
 * Created by Juhezi on 2017/4/25.
 */
public class JSON {

    /**
     * 转换为 JSON 数组
     *
     * @param list
     * @return
     */
    public static String toJSON(List<JSONable> list) {
        StringBuilder sb = new StringBuilder("");
        sb.append("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).toJSON());
            if (i != list.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
