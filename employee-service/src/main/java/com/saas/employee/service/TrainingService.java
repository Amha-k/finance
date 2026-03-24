package com.saas.employee.service;

import com.saas.employee.dto.request.TrainingRequest;
import com.saas.employee.dto.response.TrainingResponse;
import com.saas.employee.exception.ResourceNotFoundException;
import com.saas.employee.mapper.TrainingMapper;
import com.saas.employee.model.Employee;
import com.saas.employee.model.Training;
import com.saas.employee.repository.TrainingRepository;
import com.saas.employee.utility.FileUtil;
import com.saas.employee.utility.ValidationUtil;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class TrainingService {

    private final TrainingRepository trainingRepository;
    private final TrainingMapper trainingMapper;
    private final ValidationUtil validationUtil;

    public TrainingResponse addTraining(UUID tenantId,
                                        UUID employeeId,
                                        TrainingRequest request,
                                        MultipartFile file) throws IOException {

        Employee employee = validationUtil.getEmployeeById(tenantId, employeeId);
        Training training = trainingMapper.mapToEntity(tenantId, employee, request, file);
        training = trainingRepository.save(training);
        return trainingMapper.mapToDto(training);
    }

    public List<TrainingResponse> getAllTrainings(UUID tenantId,
                                                  UUID employeeId) {

        Employee employee = validationUtil.getEmployeeById(tenantId, employeeId);
        List<Training> trainings = trainingRepository.findByEmployeeId(employee.getId());
        return trainings.stream()
                .filter(tr -> tr.getTenantId().equals(tenantId))
                .map(trainingMapper::mapToDto)
                .toList();
    }

    public List<TrainingResponse> getEmployeeTrainings(UUID tenantId,
                                                       String employeeId) {

        Employee employee = validationUtil.getEmployeeByEmployeeId(tenantId, employeeId);
        List<Training> trainings = trainingRepository.findByEmployeeId(employee.getId());
        return trainings.stream()
                .filter(tr -> tr.getTenantId().equals(tenantId))
                .map(trainingMapper::mapToDto)
                .toList();
    }

    public TrainingResponse getTrainingById(UUID tenantId,
                                            UUID employeeId,
                                            UUID trainingId) {

        Employee employee = validationUtil.getEmployeeById(tenantId, employeeId);
        Training training = getTrainingById(tenantId, employee, trainingId);
        return trainingMapper.mapToDto(training);
    }

    public byte[] getTrainingCertificateById(UUID tenantId,
                                             UUID employeeId,
                                             UUID trainingId) {

        Employee employee = validationUtil.getEmployeeById(tenantId, employeeId);
        Training training = getTrainingById(tenantId, employee, trainingId);
        byte[] fileBytes = FileUtil.decompressFile(training.getFileBytes());
        if (fileBytes.length == 0) {
            throw new ResourceNotFoundException("Training file is not available");
        }
        return fileBytes;
    }

    public TrainingResponse updateTraining(UUID tenantId,
                                           UUID employeeId,
                                           UUID trainingId,
                                           TrainingRequest request,
                                           MultipartFile file) throws IOException {

        Employee employee = validationUtil.getEmployeeById(tenantId, employeeId);
        Training training = getTrainingById(tenantId, employee, trainingId);
        training = trainingMapper.mapUpdateRequest(training, request, file);
        training = trainingRepository.save(training);
        return trainingMapper.mapToDto(training);
    }

    public void deleteTraining(UUID tenantId,
                               UUID employeeId,
                               UUID trainingId) {

        Employee employee = validationUtil.getEmployeeById(tenantId, employeeId);
        Training training = getTrainingById(tenantId, employee, trainingId);
        trainingRepository.delete(training);
    }

    private Training getTrainingById(UUID tenantId,
                                     Employee employee,
                                     UUID trainingId) {

        return trainingRepository
                .findById(trainingId)
                .filter(tr -> tr.getTenantId().equals(tenantId))
                .filter(tr -> tr.getEmployee().equals(employee))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Training not found with id '" + trainingId + "'"));
    }
}
