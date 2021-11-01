package com.ileiwe.service.instructor;

import com.ileiwe.data.dto.InstructorPartyDto;
import com.ileiwe.data.model.Instructor;

public interface InstructorService {
    Instructor save(InstructorPartyDto instructor);
}
