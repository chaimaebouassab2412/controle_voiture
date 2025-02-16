public LoginDTO LoginDTO(Utilisateur utilisateur) {
    LoginDTO loginDTO = new LoginDTO();
    loginDTO.setId(utilisateur.getId());
    loginDTO.setEmail(utilisateur.getEmail());
    loginDTO.setNom(utilisateur.getNom());
    loginDTO.setPrenom(utilisateur.getPrenom());
    // Add any other necessary field mappings
    return loginDTO;
}

public LoginDTO LoginDTO(String email, String password) {
    // Different implementation with different parameters
} 