package com.mitocode.repository;

import com.mitocode.model.ResetMail;

public interface IResetMailRepo extends IGenericRepo<ResetMail, Integer>{

    //from ResetMail rm where rm.random = :?
    ResetMail findByRandom(String random);

}
