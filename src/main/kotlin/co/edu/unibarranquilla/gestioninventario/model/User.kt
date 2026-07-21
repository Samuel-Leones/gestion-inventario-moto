    package co.edu.unibarranquilla.gestioninventario.model

    import jakarta.persistence.*
    import java.time.LocalDateTime

    @Entity
    @Table(name = "users")
    class User(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        @Column(nullable = false, unique = true, length = 120)
        var email: String = "",

        @Column(nullable = false, length = 120)
        var fullName: String = "",

        @Column(nullable = false)
        var password: String = "",

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        var role: Role = Role.EMPLOYEE,

        @Column(nullable = false)
        var active: Boolean = true,

        @Column(nullable = false, updatable = false)
        var createdAt: LocalDateTime = LocalDateTime.now(),

        @Column
        var updatedAt: LocalDateTime? = null

    ) {

        @PrePersist
        fun prePersist() {
            createdAt = LocalDateTime.now()
        }

        @PreUpdate
        fun preUpdate() {
            updatedAt = LocalDateTime.now()
        }
    }