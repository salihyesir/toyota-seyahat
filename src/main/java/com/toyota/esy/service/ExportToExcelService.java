package com.toyota.esy.service;

import com.toyota.esy.dao.UsersDAO;
import com.toyota.esy.dto.TraveldetailsDTO;
import com.toyota.esy.model.Users;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class ExportToExcelService {

    @Autowired
    private TraveldetailsService traveldetailsService;

    @Autowired
    private UsersDAO usersDAO;

    private String printStandardDate(Date date) {
        return new SimpleDateFormat("dd/MM/yy HH:mm").format(date);
    }

    public Response exportExcel(int id, java.sql.Date TravelStartDate , java.sql.Date TravelFinishDate){
        Users user =  usersDAO.find(id);
        List<TraveldetailsDTO> traveldetailsDTOList;

        //Değiştirilemez olması için final
        //Dosyayı yaptık daha sonra içine yazıcaz final olmadan illegal oluyor
        //http://stackoverflow.com/questions/25830046/can-i-make-a-file-final-if-i-want-to-write-to-it-later
        final File file = new File("seyahat-kayitlari.xls");

       //Hiçbirşey seçilmedi
        if (id ==0 && TravelStartDate.getTime()==0 && TravelFinishDate.getTime()==0) {
            traveldetailsDTOList = traveldetailsService.getUnDeleted();
        }
        //Kullanıcı seçildi ama seyahatler seçilmedi
        else if(id !=0 && TravelStartDate.getTime()==0 && TravelFinishDate.getTime()==0)
        {
            traveldetailsDTOList = traveldetailsService.searchUserById(id);
        }
        //Kullanıcı seçilmedi ama seyahat zamanları seçildi
        else if (id == 0  && !(TravelStartDate.getTime()==0) && !(TravelFinishDate.getTime()==0))
            traveldetailsDTOList =traveldetailsService.searchUserByDateForAdmin(TravelStartDate, TravelFinishDate);
        //Geriye kalan tek seçenek herşey seçildi
        else
            traveldetailsDTOList = traveldetailsService.searchUserByIdForAdmin(id,TravelStartDate, TravelFinishDate);

        //xls : 2003 ve öncesi xlsx 2007 ve sonrası
        //Travel Dates için türkçe tarih
        Locale locale = new Locale("tr", "TR");
        //Exel 2007 için XSSF 2003 için HSSF
        //WorkbookFactory
        //Dosya okuma veya yazma için bir Workbook açmak lazım
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Sayfa oluşturuluyor Xssfsheet workbook tarafından verildi
        List<XSSFSheet> sheets=new ArrayList<XSSFSheet>();
        sheets.add(workbook.createSheet("Seyahatler"));
        //Key tipi string value  object dizi
        List<TreeMap<String, Object[]>> datas= new ArrayList<TreeMap<String, Object[]>>();

        datas.add(new TreeMap<String, Object[]>());
        //Başlıklar
        //key 1(ilk satıra denk gelicek)
        //Objeler(sutunlar) olucak
        datas.get(0).put("1",new Object[]{"Bölümü","Müdürü","Gezginin Adı","Seyahat Başlangıç Tarihi","Seyahat Bitiş Tarihi",
                "Seyahat Yeri","Görevi", "Proje Kodu","Tahmini Seyahat Maliyeti(₺)"});
        //Seyahat sayısı kadar satır olacak
        //i+2 diyerek ikinci satırdan başlandı i artınca yeni satıra geçilcek
        for (int i = 0; i < traveldetailsDTOList.size(); i++) {
            datas.get(0).put(String.valueOf((i + 2)), new Object[]
                    {
                            traveldetailsDTOList.get(i).getUser().getUserDepartment(),
                            traveldetailsDTOList.get(i).getUser().getDepartmentAdmin(),
                            traveldetailsDTOList.get(i).getUser().getUserName(),
                            DateFormat.getDateInstance(DateFormat.LONG, locale).format(traveldetailsDTOList.get(i).getTravelStartDate()),
                            //new SimpleDateFormat("dd/MM/yy ").format(traveldetailsDTOList.get(i).getTravelStartDate()),
                            DateFormat.getDateInstance(DateFormat.LONG, locale).format(traveldetailsDTOList.get(i).getTravelFinishDate()),
                            //new SimpleDateFormat("dd/MM/yy ").format(traveldetailsDTOList.get(i).getTravelFinishDate()),
                            traveldetailsDTOList.get(i).getTravelLocation(),
                            traveldetailsDTOList.get(i).getTravelMission(),
                            traveldetailsDTOList.get(i).getProjectCode(),
                            traveldetailsDTOList.get(i).getEstimatedCost()+" ₺"});
        }
        //Bütün keyset listesi dolaşılarak içindeki veriler excel dosyasına yazılıyor.
        Set<String> keyset = datas.get(0).keySet();
        int rownum = 0;
        //keyset ler tek tek yazılıyor
        for (String key : keyset) {
            //row satır
            //sheet sayfa proje için bir sayfa yeterli
            Row row = sheets.get(0).createRow(rownum++);

            Object[] objArr = datas.get(0).get(key);
            int cellnum = 0;

            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                sheets.get(0).autoSizeColumn(cell.getColumnIndex());

                if (obj instanceof String)
                    cell.setCellValue((String) obj);

                else if (obj instanceof Integer)
                    cell.setCellValue((Integer) obj);
            }
        }
        try {
            //Tanımlanan dosya için diske erişim sağlanıyor.
            FileOutputStream out = new FileOutputStream(file);
            //Veriler dosyaya yazılıyor.
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Başarılı cevabı gönderiliyor ve dosya response'un entity'sine set ediliyor.

        Response.ResponseBuilder responseBuilder = Response.ok(file);

        //Kullanıcıya gönderilen dosya ismi belirleniyor.
        if (id ==0 && TravelStartDate.getTime()==0 && TravelFinishDate.getTime()==0)
            responseBuilder.header("Content-Disposition", "attachment; filename=seyahat-kayitlari.xls");
        else if (id !=0 && TravelStartDate.getTime()==0 && TravelFinishDate.getTime()==0 ){
            responseBuilder.header("Content-Disposition", "attachment; filename="+user.getUserName()+"-seyahat-kayitlari.xls");
        }
        else if (id == 0  && !(TravelStartDate.getTime()==0) && !(TravelFinishDate.getTime()==0))
            responseBuilder.header("Content-Disposition", "attachment; filename="+TravelStartDate.toString()+"-"+TravelFinishDate.toString()+"-seyahat-kayitlari.xls");
        else
            responseBuilder.header("Content-Disposition", "attachment; filename="+user.getUserName()+"-"+TravelStartDate.toString()+"-"+TravelFinishDate.toString()+"-seyahat-kayitlari.xls");

        return responseBuilder.build();
    }



}
