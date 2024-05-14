package com.mitocode.repository;

import com.mitocode.model.MediaFile;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaFileRepository extends IGenericRepo<MediaFile, Integer> {
}
