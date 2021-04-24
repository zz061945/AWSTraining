package com.tw.zz.app

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class User(
    @Id
    @GeneratedValue
    var id: Long?,
    var firstName: String,
    var lastName: String
)
