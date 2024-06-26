package com.mitocode.repository;

import com.mitocode.model.Menu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IMenuRepo extends IGenericRepo<Menu, Integer> {

    @Query(value = """
                select distinct m.* from menu_role mr
            inner join user_role ur on ur.id_rol = mr.id_rol
            inner join menu m on m.id_menu = mr.id_menu
            inner join user_data u on u.id_user = ur.id_user
            where u.username = :username order by id_menu asc
        """, nativeQuery = true)
    List<Menu> getMenuByUserName(@Param("username") String userName);
}
