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

    @GetMapping("/mock")
    fun mockData(): Iterable<User> =
        userRepository.saveAll(
            listOf(
                User(null, "Tom", "Jerry"),
                User(null, "Jams", "Bond"),
                User(null, "Li", "Dazhuang")
            )
        )

}
