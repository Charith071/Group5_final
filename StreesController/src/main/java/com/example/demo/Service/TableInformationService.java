package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.TableInfomation;
import com.example.demo.dao.TableInformationDao;

@Service
public class TableInformationService {

	@Autowired
	private TableInformationDao tableInformationDao;
	
	//============= realtime====================
		public String getLastUptadeTime() {
			return tableInformationDao.getLastUptadeTime();
		}
		
	//================update details=======================
		public boolean update_table(TableInfomation data) {
			tableInformationDao.save(data);
			return true;
		}
		
	//===============get number of rows======================
	public Long get_number_of_rows() {
		return tableInformationDao.count();
	}
		
	//===============get instace by id====================
	public TableInfomation get_instance_by_id(String id) {
		return tableInformationDao.findByIds(Integer.parseInt(id));
	}
	
	//====================get last recode=======================
	public TableInfomation getLastRecorde() {
		return tableInformationDao.getLastRecord();
	}
		
		
}
