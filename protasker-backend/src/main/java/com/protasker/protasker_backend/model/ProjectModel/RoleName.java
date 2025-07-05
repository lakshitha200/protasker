package com.protasker.protasker_backend.model.ProjectModel;

public enum RoleName {
    // Administrative Roles
    ADMIN("System administrator with full system access"),
    SUPER_ADMIN("Highest-level admin with override powers"),

    // Project Management Roles
    PROJECT_MANAGER("Manages project lifecycle, resources, and team assignments"),
    //SCRUM_MASTER("Facilitates Agile ceremonies and sprint progress"),

    // Development Roles
    TEAM_LEAD("Leads a team of developers, manages code reviews"),
    SOFTWARE_ENGINEER("General developer role for implementing features"),
    FRONTEND_ENGINEER("Specializes in UI/UX and frontend implementation"),
    BACKEND_ENGINEER("Specializes in server-side development and APIs"),
    FULLSTACK_ENGINEER("Works across both frontend and backend"),
    MOBILE_ENGINEER("Develops mobile applications for iOS and Android"),

    // QA & Testing Roles
    QA_ENGINEER("Ensures product quality through manual and automated testing"),
    TEST_AUTOMATION_ENGINEER("Builds and maintains automated test suites"),

    // DevOps & Infra Roles
    DEVOPS_ENGINEER("Handles CI/CD, cloud, infrastructure, and deployment"),
    SYSTEM_ADMIN("Manages server and infrastructure setup"),

    // UX & Design
    UI_UX_DESIGNER("Designs interfaces and improves user experience"),

    // Business & Support Roles
    BUSINESS_ANALYST("Gathers requirements and aligns with business goals"),
    //PRODUCT_OWNER("Owns the product vision and backlog prioritization"),
    //TECHNICAL_WRITER("Creates technical documentation and user manuals"),
    SUPPORT_ENGINEER("Assists users and handles issue resolution"),

    // Collaboration Roles
    TEAM_MEMBER("Default role for general team members with limited access"),
    CLIENT("External client with read-only or limited permissions"),
    INTERN("Temporary contributor with limited access and rights");

    private final String description;

    RoleName(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
