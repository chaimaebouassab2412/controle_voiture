package com.controwltech.controwl.mappers;

import com.controwltech.controwl.dto.UserManagementDTO;
import com.controwltech.controwl.entities.Utilisateur;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {
    UserManagementDTO UtilisateurToUserManagementDTO(Utilisateur utilisateur);
    Utilisateur UserManagementDTOToUtilisateur(UserManagementDTO userManagementDTO);
}
