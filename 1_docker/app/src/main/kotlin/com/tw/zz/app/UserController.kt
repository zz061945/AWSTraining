package com.tw.zz.app

import org.springframework.web.bind.annotation.*

@RestController
class UserController(private val userRepository: UserRepository) {

    @GetMapping("/all")
    fun getAll(): Iterable<User> = userRepository.findAll()

    @GetMapping("/user/{id}")
    fun getById(@PathVariable id: Long) = userRepository.findById(id)

    @PutMapping("/user")
    fun createUser(@RequestBody user: User) = userRepository.save(user)

}