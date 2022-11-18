package com.stefan.pma.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.stefan.pma.entities.UserAccount;

public interface UserAccountRepository extends PagingAndSortingRepository<UserAccount, Long>{

	
}
