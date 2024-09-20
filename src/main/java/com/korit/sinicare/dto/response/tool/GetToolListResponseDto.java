package com.korit.sinicare.dto.response.tool;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.sinicare.common.utill.object.Tool;
import com.korit.sinicare.dto.response.ResponseCode;
import com.korit.sinicare.dto.response.ResponseDto;
import com.korit.sinicare.dto.response.ResponseMessage;
import com.korit.sinicare.entity.ToolEntity;

import lombok.Getter;

@Getter
public class GetToolListResponseDto extends ResponseDto {

    private List<Tool> tools;

    private GetToolListResponseDto(List<ToolEntity> toolEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.tools = Tool.getList(toolEntities);
    }

    public static ResponseEntity<GetToolListResponseDto> success(List<ToolEntity> toolEntities) {
        GetToolListResponseDto responseBody = new GetToolListResponseDto(toolEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}