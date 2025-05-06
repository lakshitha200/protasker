//package com.protasker.protasker_backend.model;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.protasker.protasker_backend.model.enums.PermissionName;
//import jakarta.persistence.*;
//import lombok.*;
//import java.time.Instant;
//import java.util.Set;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "permissions")
//public class Permission {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false, unique = true)
//    private PermissionName permissionName;
//
//    @Column(nullable = false, updatable = false)
//    private Instant createdAt = Instant.now();
//
//    @Column(nullable = false)
//    private Instant updatedAt = Instant.now();
//
//    @ManyToMany(mappedBy = "permissions")
//    private Set<Role> roles;
//}
