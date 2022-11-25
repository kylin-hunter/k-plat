package io.github.kylinhunter.plat.api.bean.sort;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Order {
    ASC("asc"), DESC("desc");
    public String code;

    public boolean equalTo(String order) {
        return this.code.equalsIgnoreCase(order);
    }
}
