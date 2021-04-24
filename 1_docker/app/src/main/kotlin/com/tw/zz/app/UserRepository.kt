package com.tw.zz.app

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long>