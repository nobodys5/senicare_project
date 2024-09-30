package com.korit.sinicare.dto.request.nurse;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchNurseRequestDto {
    // patch 요청으로 해당 이름을 바꾸기 위해 전달될 dtO
    @NotBlank
    private String name;
}
