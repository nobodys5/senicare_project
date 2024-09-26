package com.korit.sinicare.common.utill.object;

import java.util.List;
import java.util.ArrayList;

import com.korit.sinicare.entity.NurseEntity;

import lombok.Getter;

@Getter
public class Nurse {
    private String nurseId;
    private String name;
    private String telNumber;

    private Nurse (NurseEntity nurseEntity) {
        this.nurseId = nurseEntity.getUserId();
        this.name = nurseEntity.getName();
        this.telNumber = nurseEntity.getTelNumber();
    }

    public static List<Nurse> getList(List<NurseEntity> nurseEntities) {

        // 리스트로 담을 nurses 생성하여 nurseentity를 담은 nurse를 nurses에 추가한다.
        List<Nurse> nurses = new ArrayList<>();
        for (NurseEntity nurseEntity: nurseEntities) {
            Nurse nurse = new Nurse(nurseEntity);
            nurses.add(nurse);
        }

        return nurses;

    }
}
