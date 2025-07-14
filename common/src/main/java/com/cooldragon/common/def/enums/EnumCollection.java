package com.cooldragon.common.def.enums;

import lombok.Getter;
import lombok.Setter;

public class EnumCollection {
    public enum MY_TYPE {
        A("A", "Apple"),
        B("B", "Banana");

        @Getter
        @Setter
        private String cd;

        @Getter
        @Setter
        private String name;

        MY_TYPE(String cd, String name) {
            this.cd = cd;
            this.name = name;
        }
    }

}
