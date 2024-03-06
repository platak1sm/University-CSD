cmake_minimum_required(VERSION 3.1.4)

project(askisi2_4)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS ON)

add_subdirectory(ask2_4)

add_executable(${PROJECT_NAME}.exe askisi2_4.cpp)

target_include_directories(${PROJECT_NAME}.exe PUBLIC ask2_4)

target_link_directories(${PROJECT_NAME}.exe PUBLIC ask2_4)

target_link_libraries(${PROJECT_NAME}.exe ask2_4Lib)