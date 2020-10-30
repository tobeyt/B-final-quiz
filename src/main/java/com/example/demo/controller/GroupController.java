package com.example.demo.controller;

import com.example.demo.dto.Group;
import com.example.demo.service.GroupService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/groups")
@CrossOrigin
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public List<Group> getAllGroups(){
        return groupService.getAllGroups();
    }

    @PostMapping("/auto-grouping")
    // TODO GTB-知识点: - 违反Restful实践, Post请求成功后应该返回201
    public List<Group> getAutoGroupingList(){
        return groupService.getAutoGroupingList();
    }
}
