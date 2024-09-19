package com.korit.sinicare.service;

import org.springframework.http.ResponseEntity;

import com.korit.sinicare.dto.request.tool.PostToolRequestDto;
import com.korit.sinicare.dto.response.ResponseDto;

public interface ToolService {
    
    ResponseEntity<ResponseDto> postTool(PostToolRequestDto dto);

}
