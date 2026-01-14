package com.docencia.tasks.adapters.mapper;

import com.docencia.tasks.adapters.in.api.TaskRequest;
import com.docencia.tasks.adapters.in.api.TaskResponse;
import com.docencia.tasks.domain.model.Task;
import com.docencia.tasks.entitys.TaskEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskRequest toRequest(Task task);
    TaskResponse toResponse(Task task);
    TaskEntity toEntity(Task task);

    Task toTask(TaskRequest request);
    Task toTask(TaskResponse response);
    Task toTask(TaskEntity entity);

    List<TaskResponse> toResponses(List<Task> taskList);

    List<Task> entityListToTasks(List<TaskEntity> entityList);
    List<Task> requestListToTasks(List<TaskRequest> requestList);
    List<Task> responseListToTasks(List<TaskResponse> responseList);
}
