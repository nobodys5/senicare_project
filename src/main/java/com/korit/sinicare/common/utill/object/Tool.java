package com.korit.sinicare.common.utill.object;

import java.util.List;
import java.util.ArrayList;

import com.korit.sinicare.entity.ToolEntity;

import lombok.Getter;

@Getter
public class Tool {
    private Integer toolNumber;
    private String name;
    private String purpose;
    private Integer count;

    // 생성자로 entity를 매개변수로 받아와서 사용하기 위한 코드
    private Tool(ToolEntity toolEntity) {
        this.toolNumber = toolEntity.getToolNumber();
        this.name = toolEntity.getName();
        this.purpose = toolEntity.getPurpose();
        this.count = toolEntity.getCount();
    }

    public static List<Tool> getList(List<ToolEntity> toolEntities) {

        List<Tool> tools = new ArrayList<>();
        // for each문을 활용하여 tooentity 에 toolentities 값들을 하나씩 뽑아오는 코드 
        for (ToolEntity toolEntity: toolEntities) {
            Tool tool = new Tool(toolEntity);
            // 반복문으로 담아온 코드듣을 tools에 추가한다
            tools.add(tool);
        }
        return tools;

    }
}