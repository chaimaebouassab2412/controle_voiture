package com.controwltech.controwl.mappers;

import com.controwltech.controwl.dto.UserManagementDTO;
import com.controwltech.controwl.entities.Utilisateur;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-20T18:46:36+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.14 (Oracle Corporation)"
)
@Component
public class UtilisateurMapperImpl implements UtilisateurMapper {

    @Override
    public UserManagementDTO UtilisateurToUserManagementDTO(Utilisateur utilisateur) {
        if ( utilisateur == null ) {
            return null;
        }

        UserManagementDTO userManagementDTO = new UserManagementDTO();

        userManagementDTO.setId( utilisateur.getId() );
        userManagementDTO.setNom( utilisateur.getNom() );
        userManagementDTO.setEmail( utilisateur.getEmail() );
        userManagementDTO.setRole( utilisateur.getRole() );
        userManagementDTO.setActive( utilisateur.isActive() );

        return userManagementDTO;
    }

    @Override
    public Utilisateur UserManagementDTOToUtilisateur(UserManagementDTO userManagementDTO) {
        if ( userManagementDTO == null ) {
            return null;
        }

        Utilisateur utilisateur = new Utilisateur();

        utilisateur.setId( userManagementDTO.getId() );
        utilisateur.setNom( userManagementDTO.getNom() );
        utilisateur.setEmail( userManagementDTO.getEmail() );
        utilisateur.setRole( userManagementDTO.getRole() );
        utilisateur.setActive( userManagementDTO.isActive() );

        return utilisateur;
    }
}
