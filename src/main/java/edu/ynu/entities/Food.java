package edu.ynu.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Food {
    private Integer foodId;
    private String foodName;
    private String foodExplain;
    private Double foodPrice;
    private Integer businessId;

    @Override
    public String toString() {
        return"\t"+this.foodId + "\t"
                +this.foodName +"\t"
                +this.foodExplain +"\t"
                +this.foodPrice +"  \t"
                +"\n";
    }
}
