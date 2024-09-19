package com.korit.sinicare.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.korit.sinicare.dto.request.tool.PostToolRequestDto;
import com.korit.sinicare.dto.response.ResponseDto;
import com.korit.sinicare.entity.ToolEntity;
import com.korit.sinicare.repository.ToolRepository;
import com.korit.sinicare.service.ToolService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ToolServiceImplement implements ToolService {

    private final ToolRepository toolRepository;

    @Override
    public ResponseEntity<ResponseDto> postTool(PostToolRequestDto dto) {

        try {
            
            ToolEntity toolEntity = new ToolEntity(dto);
            toolRepository.save(toolEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    
}
