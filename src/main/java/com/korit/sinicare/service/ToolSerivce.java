package com.korit.sinicare.service;

import javax.sound.midi.Patch;

import org.springframework.http.ResponseEntity;

import com.korit.sinicare.dto.request.tool.PatchToolRequestDto;
import com.korit.sinicare.dto.request.tool.PostToolRequestDto;
import com.korit.sinicare.dto.response.ResponseDto;
import com.korit.sinicare.dto.response.tool.GetToolListResponseDto;
import com.korit.sinicare.dto.response.tool.GetToolResponseDto;

public interface ToolSerivce {
    
    ResponseEntity<ResponseDto> postTool(PostToolRequestDto dto);
    ResponseEntity<? super GetToolListResponseDto> getToolList();
    ResponseEntity<? super GetToolResponseDto> getTool(Integer toolNumber);
    ResponseEntity<ResponseDto> patchTool(Integer toolNUmber, PatchToolRequestDto dto);
    ResponseEntity<ResponseDto> deleteTool(Integer toolNumber);
}
