package student.testing.system.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import student.testing.system.data.repository.AuthRepositoryImpl
import student.testing.system.data.repository.CoursesRepositoryImpl
import student.testing.system.data.repository.CourseManagementRepositoryImpl
import student.testing.system.data.repository.TestsRepositoryImpl
import student.testing.system.domain.repository.AuthRepository
import student.testing.system.domain.repository.CoursesRepository
import student.testing.system.domain.repository.CourseManagementRepository
import student.testing.system.domain.repository.TestsRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthRepository(repository: AuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun bindCoursesRepository(repository: CoursesRepositoryImpl): CoursesRepository

    @Binds
    abstract fun bindTestsRepository(repository: TestsRepositoryImpl): TestsRepository

    @Binds
    abstract fun bindCourseManagementRepository(repository: CourseManagementRepositoryImpl): CourseManagementRepository
}