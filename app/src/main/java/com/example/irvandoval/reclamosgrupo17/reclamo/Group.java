package com.example.irvandoval.reclamosgrupo17.reclamo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by irvandoval on 06-13-15.
 */
public class Group {
    public String string;
    public final List<String> children = new ArrayList<String>();

    public Group(String string) {
        this.string = string;
    }
}
