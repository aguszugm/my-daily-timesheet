# Project: My Daily Timesheet

## Overview
This is a fullstack application for managing daily timesheets. Please refer to the following specifications: 
- There are 3 type of user, Admin and Staff. 
- There is 1 pre-defined Admin user with special id: 0.
- User need to register into the platform before they can entry the timesheet.

## Directory Structure
- my-daily-timesheet-web for frontend web
- my-dailiy-timesheet-svc for backend (spring boot 4)

## User Access

### Admin
- CRUD Project
- CRUD Holiday
- All staff access

### Staff
- CRUD timesheet
- When staff create timesheet, they can specify 1 or more approver. The approver able to select from User domain or just only input free the approver name.
- CRUD timesheet entry
- If there is any timesheet that put the Staff as approver, they can review the timesheet (approve or reject)

## Tech Stack
### Frontend
Vue 3 + TypeScript + Vuetify (Vite)

### Backend
- Spring Boot 3
- JVM 21
- Database: PostgreSQL 15
- Database Migration Tools: Flyway
- Annotation Tools: Lombok

## Architecture

### Frontend
- Use Composition API
- Organize code into:
  - pages/
  - components/
  - services/
  - store/ (Pinia)
  - router/

### Backend
- Follow layered architecture:
  - controller
  - service
  - repository
  - entity
  - dto

## Coding Guidelines

### General
- Use clean and readable code
- Prefer meaningful variable names
- Avoid unnecessary complexity

### Frontend
- Use TypeScript types/interfaces
- Use Axios for API calls
- Separate UI and business logic

### Backend
- Use Lombok for boilerplate reduction
- Use RESTful API conventions
- Use DTO for request/response

## API Conventions
- Base URL: /api
- Use standard HTTP methods:
  - GET
  - POST
  - PUT
  - DELETE

## Domain Model
Add created_datetime, updated_datetime, created_by, and updated_by for all domain model.

User:
- id
- user_email
- password
- full_name
- signature_image (BLOB)
- is_admin (true | false)

Project:
- id
- project_business_id
- project_name

Holiday:
- id
- date
- holiday_name

Timesheet:
- id
- period_name
- period_start_date
- period_end_date
- number_of_working_day

Timesheet Approver:
- id
- full_name
- user_id (refer to User but nullable)
- approver_label (for example: Disetujui oleh, Diketahui oleh, etc.)
- sequence_order (used to put the signature order when generate report)

Timesheet Approval History:
- id
- timesheet_id (refer to Timesheet)
- date
- status (APPROVED | REJECTED)
- note

Timesheet Entry:
- id
- timesheet_id (refer to Timesheet)
- date
- vendor_name
- user_id (refer to User)
- spk
- hour_start
- hour_end
- description
- project_id (refer to Project)
- activity
- working_type (WFH | WFO | LEAVE)

## Goals
- Build a clean, scalable timesheet system
- Ensure maintainable architecture
- Enable easy extension for future features