-- phpMyAdmin SQL Dump
-- version 2.7.0-pl2
-- http://www.phpmyadmin.net
-- 
-- Host: oraclepr.uco.es
-- Generation Time: Oct 30, 2020 at 12:39 PM
-- Server version: 5.1.73
-- PHP Version: 5.3.3
-- 
-- Database: `i82gatom`
-- 
CREATE DATABASE `i82gatom` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE i82gatom;

-- 
-- Table structure for table `Usuario`
-- 

DROP TABLE IF EXISTS `Usuario`;
CREATE TABLE IF NOT EXISTS `Usuario` (
  `Email` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `Nombre` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `Apellidos` varchar(120) COLLATE utf8_spanish_ci NOT NULL,
  `FechaNacimiento` date NOT NULL,
  `Clave` varchar(120) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`Email`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- 
-- Dumping data for table `Usuario`
-- 

INSERT INTO `Usuario` VALUES ('i82gatom@uco.es', 'María', 'García Torres', '2000-11-02', 'i82gatom');
INSERT INTO `Usuario` VALUES ('i82casmm@uco.es', 'Miguel', 'Castro Martín', '2000-06-07', 'i82casmm');
INSERT INTO `Usuario` VALUES ('i82alsae@uco.es', 'Elena', 'Alvarez Sanchez', '2000-01-16', 'i82alsae');
-- 
-- Database: `information_schema`
-- 
CREATE DATABASE `information_schema` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE information_schema;