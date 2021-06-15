package com.example.hes_fin_tech_test.domain;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserAccountMapper {

    UserAccountMapper USER_ACCOUNT_MAPPER = Mappers.getMapper(UserAccountMapper.class);

    UserAccountDTO userAccountToUserAccountDTO(UserAccount userAccount);

    UserAccount userAccountDTOToUserAccount(UserAccountDTO userAccountDTO);
}
