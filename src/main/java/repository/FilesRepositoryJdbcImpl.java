package repository;

import models.FileInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class FilesRepositoryJdbcImpl implements FilesRepository{
    private DataSource dataSource;
    private final static String SQL_INSERT_FILE_UPLOAD= "INSERT INTO files_info(originalFileName,storageFileName, type, size)"+"VALUES (?,?,?,?)";

    private final static String SQL_SELECT_BY_ID= "SELECT * FROM files_info WHERE id = ?";
    private final static String SQL_SELECT= "SELECT * FROM file";
    private JdbcTemplate jdbcTemplate;

    public FilesRepositoryJdbcImpl(DriverManagerDataSource dataSource) {
this.dataSource= dataSource;    }
    private RowMapper<FileInfo>fileRowMapper= (row, rowMapper)->
            FileInfo.builder()
                    .id(row.getLong("id"))
                    .originalFileName(row.getString("originalFileName"))
                    .storageFileName(row.getString("storageFileName"))
                    .size(row.getLong("size"))
                    .type(row.getString("type"))
                    .build();
    public void save(FileInfo entity){
        jdbcTemplate.update(SQL_INSERT_FILE_UPLOAD,entity.getStorageFileName(),entity.getOriginalFileName(),entity.getType(),entity.getSize());
    }
    public FileInfo findById(Long id){
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID,fileRowMapper,id );
    }

}
