package com.enigma.shopeymart.Service.Impl.JWTImpl;

import com.enigma.shopeymart.Entity.JWT.Role;
import com.enigma.shopeymart.Repositori.JWT.RoleRepositori;
import com.enigma.shopeymart.Service.JWTService.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    public final RoleRepositori repositori;

    @Override
    public Role getOrSave(Role role) { // mengembalikan nilai
        System.out.println(role);
        Optional<Role> optionalRole = repositori.findByName(role.getName());

        // jika ada di db di get
        if (!optionalRole.isEmpty()){
            return optionalRole.get();
        }

        return  repositori.save(role);


    }
}
