package gr.ntua.ece.softeng19b.data;

import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.DATLFRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.DATLFRecordForSpecificMonth;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public class DataAccess {

    private static final Object[] EMPTY_ARGS = new Object[0];

    private static final int MAX_TOTAL_CONNECTIONS = 16;
    private static final int MAX_IDLE_CONNECTIONS = 8;

    private JdbcTemplate jdbcTemplate;

    public void setup(String driverClass, String url, String user, String pass) throws SQLException {

        //initialize the data source
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName(driverClass);
        bds.setUrl(url);
        bds.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        bds.setMaxIdle(MAX_IDLE_CONNECTIONS);
        bds.setUsername(user);
        bds.setPassword(pass);
        bds.setValidationQuery("SELECT 1");
        bds.setTestOnBorrow(true);
        bds.setDefaultAutoCommit(true);

        //check that everything works OK
        bds.getConnection().close();

        //initialize the jdbc template utility
        jdbcTemplate = new JdbcTemplate(bds);
    }

    public void accessDataCheck() throws DataAccessException {
        try {
            jdbcTemplate.query("select 1", ResultSet::next);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }


    public List<ATLRecordForSpecificDay> fetchActualDataLoadForSpecificDate(String areaName,
                                                                                        String resolution,
                                                                                        LocalDate date)
            throws DataAccessException {

        Integer year = date.getYear();
        Integer month = date.getMonthValue();
        Integer day = date.getDayOfMonth();

        Object[] sqlParams = new Object[] {
                areaName,
                resolution,
                year,
                month,
                day
        };

        //TODO: Insert a valid SQL query
        String sqlQuery = "select atl.areaname, atc.areatypecodetext, mc.mapcodetext, rc.resolutioncodetext, atl.year, atl.month, atl.day, atl.TotalLoadValue "+
                          "from actualtotalload as atl, resolutioncode as rc, areatypecode as atc, mapcode as mc " +
                          "where atl.areaname=? and rc.resolutioncodetext=? and atl.Year=? and atl.Month=? and atl.Day=? " +
                          "and rc.Id=atl.ResolutionCodeId and mc.id=atl.mapcodeid and atc.id=atl.AreaTypeCodeId";
		try {
					return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
						ATLRecordForSpecificDay dataLoad = new ATLRecordForSpecificDay();
						dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
						dataLoad.setAreaTypeCode(rs.getString(2)); //get the int located at the 2nd column of the result set
						dataLoad.setMapCode(rs.getString(3));
						dataLoad.setResolutionCode(rs.getString(4));
						dataLoad.setYear(rs.getInt(5));
						dataLoad.setMonth(rs.getInt(6));
						dataLoad.setDay(rs.getInt(7));
						dataLoad.setActualTotalLoadValue(rs.getDouble(8));
						return dataLoad;

					});
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<DATLFRecordForSpecificDay> fetchDayAheadTotalLoadForecastForSpecificDate(String areaName,
                                                                                        String resolution,
                                                                                        LocalDate date)
            throws DataAccessException {

        Integer year = date.getYear();
        Integer month = date.getMonthValue();
        Integer day = date.getDayOfMonth();

        Object[] sqlParams = new Object[] {
                areaName,
                resolution,
                year,
                month,
                day
        };

        //TODO: Insert a valid SQL query
        String sqlQuery = "select datlf.areaname, atc.areatypecodetext, mc.mapcodetext, rc.resolutioncodetext, datlf.year, datlf.month, datlf.day, datlf.TotalLoadValue "+
                          "from dayaheadtotalloadforecast as datlf, resolutioncode as rc, areatypecode as atc, mapcode as mc " +
                          "where datlf.areaname=? and rc.resolutioncodetext=? and datlf.Year=? and datlf.Month=? and datlf.Day=? " +
                          "and rc.Id=datlf.ResolutionCodeId and mc.id=datlf.mapcodeid and atc.id=datlf.AreaTypeCodeId";
		try {
					return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
						DATLFRecordForSpecificDay dataLoad = new DATLFRecordForSpecificDay();
						dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
						dataLoad.setAreaTypeCode(rs.getString(2)); //get the int located at the 2nd column of the result set
						dataLoad.setMapCode(rs.getString(3));
						dataLoad.setResolutionCode(rs.getString(4));
						dataLoad.setYear(rs.getInt(5));
						dataLoad.setMonth(rs.getInt(6));
						dataLoad.setDay(rs.getInt(7));
						dataLoad.setDayAheadTotalLoadForecastValue(rs.getDouble(8));
						return dataLoad;

					});
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }
    public List<DATLFRecordForSpecificMonth> fetchDayAheadTotalLoadForecastForSpecificMonth(String areaName,
                                                                                        String resolution,
                                                                                        YearMonth yearMonth)
            throws DataAccessException {

        Integer year = yearMonth.getYear();
        Integer month = yearMonth.getMonthValue();

        Object[] sqlParams = new Object[] {
                areaName,
                resolution,
                year,
                month
        };

        //TODO: Insert a valid SQL query
        String sqlQuery = "select datlf.areaname, atc.areatypecodetext, mc.mapcodetext, rc.resolutioncodetext, datlf.year, "+
                          "datlf.month, datlf.day, sum(datlf.TotalLoadValue) as TotalLoadForecastValue "+
                          "from dayaheadtotalloadforecast as datlf, resolutioncode as rc, areatypecode as atc, mapcode as mc " +
                          "where datlf.areaname=? and rc.resolutioncodetext=? and datlf.Year=? and datlf.Month=? " +
                          "and rc.Id=datlf.ResolutionCodeId and mc.id=datlf.mapcodeid and atc.id=datlf.AreaTypeCodeId " +
                          "group by datlf.day, atc.areatypecodetext, mc.mapcodetext, rc.resolutioncodetext order by datlf.day asc";
		try {
					return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
						DATLFRecordForSpecificMonth dataLoad = new DATLFRecordForSpecificMonth();
						dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
						dataLoad.setAreaTypeCode(rs.getString(2)); //get the int located at the 2nd column of the result set
						dataLoad.setMapCode(rs.getString(3));
						dataLoad.setResolutionCode(rs.getString(4));
						dataLoad.setYear(rs.getInt(5));
						dataLoad.setMonth(rs.getInt(6));
						dataLoad.setDay(rs.getInt(7));
						dataLoad.setDayAheadTotalLoadForecastValue(rs.getDouble(8));
						return dataLoad;

					});
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

}
