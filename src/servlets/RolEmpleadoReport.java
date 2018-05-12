package servlets;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import dao.ClienteDAO;
import models.*;
import utilidad.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

@WebServlet ("/rol/rol_empleado")
public class RolEmpleadoReport extends HttpServlet{

    private List<RolCliente> rolesCliente;
    private RolIndividual rolIndividual;
    private PagoMes pagoMes;
    Boolean hasDecimo = false;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        rolesCliente = (List<RolCliente>) request.getSession().getAttribute(Const.PRINT_RL);
        rolIndividual = (RolIndividual) request.getSession().getAttribute(Const.PRINT_RI);
        pagoMes = (PagoMes) request.getSession().getAttribute(Const.PRINT_PM);

        if(request.getParameter("print") != null){

            if(rolesCliente.size() != 0) {
                calculate();
                String fechaRango =  "del "+ Fecha.toTextRangeNormalized(rolIndividual.getInicio());

                response.setContentType("application/pdf");

                OutputStream outputStream = response.getOutputStream();
                Document document = new Document(PageSize.A4.rotate(), 30, 30, 25, 25);
                try {
                    PdfWriter.getInstance(document, outputStream);

                    document.open();

                    mainHeader(document);
                    descriptionFixedHeader(document, rolIndividual.getEmpleado(), rolIndividual.getCedula(), fechaRango);

                    for (RolCliente rolCliente :
                            rolesCliente) {

                        tableValues(document, rolCliente.getDias().toString(), rolCliente.getSalario().toString(), rolCliente.getHorasSobreTiempo().toString(), rolCliente.getHorasSuplementarias().toString(),
                                rolCliente.getTotalBonos().toString(), rolCliente.getVacaciones().toString(), rolCliente.getSubtotal().toString(), rolCliente.getDecimoTercero().toString(), rolCliente.getDecimoCuarto().toString(),
                                rolCliente.getDecimoTercero().toString(), rolCliente.getJubilacionPatronal().toString(), rolCliente.getAportePatronal().toString(), rolCliente.getSeguros().toString(), rolCliente.getUniformes().toString(),
                                rolCliente.getTotalIngreso().toString(), rolCliente.getClienteNombre(), rolCliente.getHorasNormales().toString(), rolCliente.getMontoHorasSobreTiempo().toString(), rolCliente.getMontoHorasSuplementarias().toString());

                    }

                    showTotal(document, request, response);

                    document.close();
                    outputStream.flush();

                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }else{
                PrintWriter out = response.getWriter();
                out.print("<script> alert('No hay resultados para esta fecha'); </script>");

                String typeInfo = "noDateToPDF";
                request.setAttribute("info_msg", typeInfo);
            }
        }

    }

    public void calculate() {
        hasDecimo = false;
        for (PagoMesItem item: pagoMes.getPagosItems()) {
            if (item.getIngreso() != null) {
                if (item.getClave().equals("decimo_tercero")) {
                    hasDecimo = true;
                }
                if (item.getClave().equals("decimo_cuarto")) {
                    hasDecimo = true;
                }
            }
        }
    }

    private void mainHeader(Document document){

        Calendar calendar = Calendar.getInstance();
        String day = Integer.toString(calendar.get(Calendar.DATE));
        String month = Integer.toString(calendar.get(Calendar.MONTH)+1) ;
        String year = Integer.toString(calendar.get(Calendar.YEAR));

        Font dateFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        Font reportTitleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.UNDERLINE);

        //FIRST FIXED HEADER
        Paragraph dateReport = new Paragraph(day+"/"+month+"/"+year, dateFont);
        dateReport.setAlignment(Element.ALIGN_RIGHT);

        Paragraph reportTitle = new Paragraph("Rol Empleado.", reportTitleFont);
        reportTitle.setAlignment(Element.ALIGN_CENTER);
        reportTitle.setSpacingBefore(5);

        try {
            document.add(dateReport);
            document.add(reportTitle);
        }catch (DocumentException de){
            de.printStackTrace();
        }
    }

    private void descriptionFixedHeader(Document document, String empleadoName, String cedula, String lapso) {

        Font clientFont = new Font(Font.FontFamily.HELVETICA, 12);
        Font strcFont = new Font(Font.FontFamily.HELVETICA, 11);
        Font tableFont = new Font(Font.FontFamily.HELVETICA, 10);

        //DESCRIPTION FIXED HEADER
        Chunk glue = new Chunk(new VerticalPositionMark());
        Paragraph clientFullName = new Paragraph("Empleado: " + empleadoName, clientFont);
        clientFullName.add(new Chunk(glue));
        clientFullName.add(new Phrase("Empresa: " + rolIndividual.getUsuario().getDetallesEmpleado().getEmpresa().getNombre(), strcFont));
        clientFullName.setSpacingBefore(8);
        clientFullName.setIndentationLeft(20);

        Paragraph numberRuc = new Paragraph("Numeración: " + cedula, clientFont);
        numberRuc.add(new Chunk(glue));
        numberRuc.add(new Phrase("Cargo: " + rolIndividual.getUsuario().getDetallesEmpleado().getCargo().getNombre(), strcFont));
        numberRuc.setIndentationLeft(20);

        Paragraph laps = new Paragraph("Lapso: " + lapso, clientFont);
        laps.setIndentationLeft(20);

        //TABLE HEADER
        float[] columnWith = {2, 0.7f, 1.1f, 1.4f, 1.4f, 1.2f, 1.3f, 1.2f, 1.1f, 1.4f, 1.3f, 1.3f, 1.1f, 1.3f, 1.4f, 1.3f};
        PdfPTable table = new PdfPTable(columnWith);
        table.setSpacingBefore(10);
        table.setWidthPercentage(100);

        PdfPCell empleado = new PdfPCell(new Phrase("Cliente", tableFont));
        PdfPCell dias = new PdfPCell(new Phrase("Dias", tableFont));
        PdfPCell salario = new PdfPCell(new Phrase("Salario", tableFont));
        PdfPCell stHoras = new PdfPCell(new Phrase("ST Horas", tableFont));
        PdfPCell rcHoras = new PdfPCell(new Phrase("RC Horas", tableFont));
        PdfPCell bonos = new PdfPCell(new Phrase("Bonos", tableFont));
        PdfPCell subTotal = new PdfPCell(new Phrase("Subtotal", tableFont));
        PdfPCell decimoTercero = new PdfPCell(new Phrase("Decimo\nTercero", tableFont));
        PdfPCell decimoCuarto = new PdfPCell(new Phrase("Decimo\nCuarto", tableFont));
        PdfPCell vacacion = new PdfPCell(new Phrase("Vacacion", tableFont));
        PdfPCell fondoRecerva = new PdfPCell(new Phrase("Fondo de\n Reserva", tableFont));
        PdfPCell jubilacionPatronal = new PdfPCell(new Phrase("Jubilacion\nPatronal", tableFont));
        PdfPCell aportePatronal = new PdfPCell(new Phrase("Aporte\nPatronal", tableFont));
        PdfPCell seguros = new PdfPCell(new Phrase("Seguros", tableFont));
        PdfPCell uniformesGuardias = new PdfPCell(new Phrase("Uniformes\nGuardias", tableFont));
        PdfPCell totalIngresos = new PdfPCell(new Phrase("Total\nIngresos", tableFont));

        empleado.setMinimumHeight(35);
        empleado.setPaddingTop(10);
        dias.setPaddingTop(10);
        salario.setPaddingTop(10);
        stHoras.setPaddingTop(10);
        rcHoras.setPaddingTop(10);
        bonos.setPaddingTop(10);
        vacacion.setPaddingTop(10);
        subTotal.setPaddingTop(10);
        decimoTercero.setPaddingTop(5);
        decimoCuarto.setPaddingTop(5);
        fondoRecerva.setPaddingTop(5);
        jubilacionPatronal.setPaddingTop(5);
        aportePatronal.setPaddingTop(5);
        seguros.setPaddingTop(10);
        uniformesGuardias.setPaddingTop(5);
        totalIngresos.setPaddingTop(5);

        empleado.setHorizontalAlignment(Element.ALIGN_CENTER);
        dias.setHorizontalAlignment(Element.ALIGN_CENTER);
        salario.setHorizontalAlignment(Element.ALIGN_CENTER);
        stHoras.setHorizontalAlignment(Element.ALIGN_CENTER);
        rcHoras.setHorizontalAlignment(Element.ALIGN_CENTER);
        bonos.setHorizontalAlignment(Element.ALIGN_CENTER);
        vacacion.setHorizontalAlignment(Element.ALIGN_CENTER);
        subTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
        decimoTercero.setHorizontalAlignment(Element.ALIGN_CENTER);
        decimoCuarto.setHorizontalAlignment(Element.ALIGN_CENTER);
        fondoRecerva.setHorizontalAlignment(Element.ALIGN_CENTER);
        jubilacionPatronal.setHorizontalAlignment(Element.ALIGN_CENTER);
        aportePatronal.setHorizontalAlignment(Element.ALIGN_CENTER);
        seguros.setHorizontalAlignment(Element.ALIGN_CENTER);
        uniformesGuardias.setHorizontalAlignment(Element.ALIGN_CENTER);
        totalIngresos.setHorizontalAlignment(Element.ALIGN_CENTER);

        empleado.setBackgroundColor(BaseColor.CYAN);
        dias.setBackgroundColor(BaseColor.CYAN);
        salario.setBackgroundColor(BaseColor.CYAN);
        stHoras.setBackgroundColor(BaseColor.CYAN);
        rcHoras.setBackgroundColor(BaseColor.CYAN);
        bonos.setBackgroundColor(BaseColor.CYAN);
        subTotal.setBackgroundColor(BaseColor.CYAN);
        if (hasDecimo) {
            decimoTercero.setBackgroundColor(BaseColor.CYAN);
            decimoCuarto.setBackgroundColor(BaseColor.CYAN);
        } else {
            decimoTercero.setBackgroundColor(new BaseColor(205, 92, 92));
            decimoCuarto.setBackgroundColor(new BaseColor(205, 92, 92));
        }
        vacacion.setBackgroundColor(new BaseColor(205, 92, 92));
        fondoRecerva.setBackgroundColor(new BaseColor(205, 92, 92));
        jubilacionPatronal.setBackgroundColor(new BaseColor(205, 92, 92));
        aportePatronal.setBackgroundColor(new BaseColor(205, 92, 92));
        seguros.setBackgroundColor(new BaseColor(205, 92, 92));
        uniformesGuardias.setBackgroundColor(new BaseColor(205, 92, 92));
        totalIngresos.setBackgroundColor(BaseColor.YELLOW);

        table.addCell(empleado);
        table.addCell(dias);
        table.addCell(salario);
        table.addCell(stHoras);
        table.addCell(rcHoras);
        table.addCell(bonos);
        table.addCell(subTotal);
        table.addCell(decimoTercero);
        table.addCell(decimoCuarto);
        table.addCell(vacacion);
        table.addCell(fondoRecerva);
        table.addCell(jubilacionPatronal);
        table.addCell(aportePatronal);
        table.addCell(seguros);
        table.addCell(uniformesGuardias);
        table.addCell(totalIngresos);

        try {
            document.add(clientFullName);
            document.add(numberRuc);
            document.add(laps);
            document.add(table);
        }catch (DocumentException docEx){
            docEx.printStackTrace();
        }
    }

    private void tableValues(Document document, String dias, String salario, String stHoras, String rcHoras, String bonos,
    String vacacion, String subTotal, String decimTercero, String decimCuarto, String fondoRecerva, String jubilaPatronal, String aportPatronal,
    String seguros, String uniformes, String totalIngresos, String nombreClient, String salario2, String stHoras2, String rcHoras2) {

        Font tableFont = new Font(Font.FontFamily.HELVETICA, 10);

        //TABLE BODY
        float[] columnWith = {2, 0.7f, 1.1f, 1.4f, 1.4f, 1.2f, 1.3f, 1.2f, 1.1f, 1.4f, 1.3f, 1.3f, 1.1f, 1.3f, 1.4f, 1.3f};
        PdfPTable tableBody = new PdfPTable(columnWith);
        tableBody.setSpacingBefore(10);
        tableBody.setWidthPercentage(100);

        PdfPCell cedulaCell = new PdfPCell(new Phrase("", tableFont));
        PdfPCell diasCell = new PdfPCell(new Phrase(dias, tableFont));
        PdfPCell salarioCell = new PdfPCell(new Phrase("$"+salario, tableFont));
        PdfPCell stHorasCell = new PdfPCell(new Phrase("$"+stHoras, tableFont));
        PdfPCell rcHorasCell = new PdfPCell(new Phrase("$"+rcHoras, tableFont));
        PdfPCell transporteCell = new PdfPCell(new Phrase("$"+bonos, tableFont));
        PdfPCell subTotalCell = new PdfPCell(new Phrase("$"+subTotal, tableFont));
        PdfPCell decimoTerceroCell = new PdfPCell(new Phrase("$"+decimTercero, tableFont));
        PdfPCell decimoCuartoCell = new PdfPCell(new Phrase("$"+decimCuarto, tableFont));
        PdfPCell vacacionCell = new PdfPCell(new Phrase("$"+vacacion, tableFont));
        PdfPCell fondoRecervaCell = new PdfPCell(new Phrase("$"+fondoRecerva, tableFont));
        PdfPCell jubilacionPatronalCell = new PdfPCell(new Phrase("$"+jubilaPatronal, tableFont));
        PdfPCell aportePatronalCell = new PdfPCell(new Phrase("$"+aportPatronal, tableFont));
        PdfPCell segurosCell = new PdfPCell(new Phrase("$"+seguros, tableFont));
        PdfPCell uniformesGuardiasCell = new PdfPCell(new Phrase("$"+uniformes, tableFont));
        PdfPCell totalIngresosCell = new PdfPCell(new Phrase("$"+totalIngresos, tableFont));

        PdfPCell nombreEmpleadoCell = new PdfPCell(new Phrase(nombreClient, tableFont));
        PdfPCell salarioSecondRow = new PdfPCell(new Phrase(salario2, tableFont));
        PdfPCell stHorasSecondRow = new PdfPCell(new Phrase(stHoras2, tableFont));
        PdfPCell rcHorasSecondRow = new PdfPCell(new Phrase(rcHoras2, tableFont));
        PdfPCell cuentaAhorroSecondRow = new PdfPCell(new Phrase("", tableFont));

        cedulaCell.setFixedHeight(23);
        cedulaCell.setPaddingTop(5);
        diasCell.setPaddingTop(5);
        salarioCell.setPaddingTop(5);
        stHorasCell.setPaddingTop(5);
        rcHorasCell.setPaddingTop(5);
        transporteCell.setPaddingTop(5);
        vacacionCell.setPaddingTop(5);
        subTotalCell.setPaddingTop(5);
        decimoTerceroCell.setPaddingTop(5);
        decimoCuartoCell.setPaddingTop(5);
        fondoRecervaCell.setPaddingTop(5);
        jubilacionPatronalCell.setPaddingTop(5);
        aportePatronalCell.setPaddingTop(5);
        segurosCell.setPaddingTop(5);
        uniformesGuardiasCell.setPaddingTop(5);
        totalIngresosCell.setPaddingTop(5);
        salarioSecondRow.setFixedHeight(20);
        nombreEmpleadoCell.setFixedHeight(30);
        nombreEmpleadoCell.setPaddingTop(5);
        salarioSecondRow.setPaddingTop(14);
        stHorasSecondRow.setPaddingTop(14);
        rcHorasSecondRow.setPaddingTop(14);
        cuentaAhorroSecondRow.setPaddingTop(14);

        cedulaCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        diasCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        salarioCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        stHorasCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        rcHorasCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        transporteCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        vacacionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        subTotalCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        decimoTerceroCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        decimoCuartoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        fondoRecervaCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        jubilacionPatronalCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        aportePatronalCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        segurosCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        uniformesGuardiasCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        totalIngresosCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        nombreEmpleadoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        salarioSecondRow.setHorizontalAlignment(Element.ALIGN_CENTER);
        stHorasSecondRow.setHorizontalAlignment(Element.ALIGN_CENTER);
        rcHorasSecondRow.setHorizontalAlignment(Element.ALIGN_CENTER);
        cuentaAhorroSecondRow.setHorizontalAlignment(Element.ALIGN_CENTER);

        tableBody.addCell(cedulaCell);
        tableBody.addCell(diasCell);
        tableBody.addCell(salarioCell);
        tableBody.addCell(stHorasCell);
        tableBody.addCell(rcHorasCell);
        tableBody.addCell(transporteCell);
        tableBody.addCell(subTotalCell);
        tableBody.addCell(decimoTerceroCell);
        tableBody.addCell(decimoCuartoCell);
        tableBody.addCell(vacacionCell);
        tableBody.addCell(fondoRecervaCell);
        tableBody.addCell(jubilacionPatronalCell);
        tableBody.addCell(aportePatronalCell);
        tableBody.addCell(segurosCell);
        tableBody.addCell(uniformesGuardiasCell);
        tableBody.addCell(totalIngresosCell);
        nombreEmpleadoCell.setColspan(2);
        tableBody.addCell(nombreEmpleadoCell);
        tableBody.addCell(salarioSecondRow);
        tableBody.addCell(stHorasSecondRow);
        tableBody.addCell(rcHorasSecondRow);
        cuentaAhorroSecondRow.setColspan(11);
        tableBody.addCell(cuentaAhorroSecondRow);
        tableBody.completeRow();

        try {
            document.add(tableBody);
        }catch (DocumentException docEx ) {
            docEx.printStackTrace();
        }
    }

    private void showTotal(Document document, HttpServletRequest request, HttpServletResponse response) throws IOException {

        Double decimo3 = null;
        Double decimo4 = null;
        Double ingreso = 0d;
        Double iess = 0d;
        Double quincena = 0d;
        Double deudas = 0d;
        Double deducciones = 0d;
        Double recibido = 0d;
        Double totalFinalRetenido = 0d;
        String iessTitle = "IESS";

        for (PagoMesItem item: pagoMes.getPagosItems()) {
            if (item.getIngreso() != null) {
                ingreso += item.getIngreso();
                if (item.getClave().equals("decimo_tercero")) {
                    decimo3 = item.getIngreso();
                }
                if (item.getClave().equals("decimo_cuarto")) {
                    decimo4 = item.getIngreso();
                }
            }
            if (item.getDeduccion() != null) {
                if (item.getClave().equals("iess")) {
                    iess += item.getDeduccion();
                    iessTitle = item.getDescripcion();
                }
                if (item.getClave().equals("adelanto_quincenal"))
                    quincena += item.getDeduccion();
                if (item.getClave().equals("deuda"))
                    deudas += item.getDeduccion();
                deducciones += item.getDeduccion();
            }
        }
        recibido = ingreso - deducciones;

        ingreso = Numeros.round(ingreso);
        iess = Numeros.round(iess);
        quincena = Numeros.round(quincena);
        deudas = Numeros.round(deudas);
        deducciones = Numeros.round(deducciones);
        recibido = Numeros.round(recibido);

        for (RolCliente rol : rolesCliente) {
            Double totalRecibido = 0d;
            Double totalRetenido = 0d;
            totalRecibido += rol.getSalario()
                    + rol.getMontoHorasSobreTiempo()
                    + rol.getMontoHorasSuplementarias()
                    + rol.getBono()
                    + rol.getTransporte();
            totalRetenido += (rol.getVacaciones()
                    + rol.getDecimoTercero()
                    + rol.getAportePatronal()
                    + rol.getJubilacionPatronal()
                    + rol.getSeguros()
                    + rol.getUniformes());


            if (decimo3 != null) {
                totalRecibido += rol.getDecimoTercero();

            } else {
                totalRetenido += rol.getDecimoTercero();
            }
            if (decimo4 != null) {
                totalRecibido += rol.getDecimoCuarto();
            } else {
                totalRetenido += rol.getDecimoCuarto();
            }
            totalFinalRetenido += totalRetenido;
        }

        Font totalesFont = new Font(Font.FontFamily.HELVETICA, 12);
        Font tableFont = new Font(Font.FontFamily.HELVETICA, 10);

        try {
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        Paragraph totales = new Paragraph("Rol de pago Mensual", totalesFont);
        totales.setSpacingBefore(15);

        //TABLE HEADER
        float[] columnWith;
        if (decimo3 == null)
            columnWith = new float[]{1.1f, 1.4f, 1.4f, 1.4f, 1.2f, 1.3f, 1.1f, 1.1f, 1.3f};
        else
            columnWith = new float[]{1.1f, 1.4f, 1.4f, 1.4f, 1.2f, 1.3f, 1.1f, 1.1f, 1.3f, 1.3f, 1.3f};

        PdfPTable table = new PdfPTable(columnWith);
        table.setSpacingBefore(10);
        table.setWidthPercentage(100);

        PdfPTable tableBody = new PdfPTable(columnWith);
        tableBody.setSpacingBefore(0);
        tableBody.setWidthPercentage(100);

        PdfPCell dias = new PdfPCell(new Phrase("Dias", tableFont));
        PdfPCell horasN = new PdfPCell(new Phrase("Horas(N)", tableFont));
        PdfPCell horasST = new PdfPCell(new Phrase("Horas(ST)", tableFont));
        PdfPCell horasRC = new PdfPCell(new Phrase("Horas(RC)", tableFont));
        PdfPCell salario = new PdfPCell(new Phrase("Salario", tableFont));
        PdfPCell stHoras = new PdfPCell(new Phrase("ST Horas", tableFont));
        PdfPCell rcHoras = new PdfPCell(new Phrase("RC Horas", tableFont));
        PdfPCell bonos = new PdfPCell(new Phrase("Bonos", tableFont));
        PdfPCell subTotal = new PdfPCell(new Phrase("Subtotal", tableFont));
        PdfPCell decimoTercero = new PdfPCell(new Phrase("Decimo\nTercero", tableFont));
        PdfPCell decimoCuarto = new PdfPCell(new Phrase("Decimo\nCuarto", tableFont));

        PdfPCell diasCell = new PdfPCell(new Phrase(Numeros.round(rolIndividual.getDias()).toString(), tableFont));
        PdfPCell horasNCell = new PdfPCell(new Phrase(Numeros.round(rolIndividual.getHorasNormales()).toString(), tableFont));
        PdfPCell horasSTCell = new PdfPCell(new Phrase(Numeros.round(rolIndividual.getHorasSobreTiempo()).toString(), tableFont));
        PdfPCell horasRCCell = new PdfPCell(new Phrase(Numeros.round(rolIndividual.getHorasSuplementarias()).toString(), tableFont));
        PdfPCell salarioCell = new PdfPCell(new Phrase("$"+Numeros.round(rolIndividual.getSalario()).toString(), tableFont));
        PdfPCell stHorasCell = new PdfPCell(new Phrase("$"+Numeros.round(rolIndividual.getMontoHorasSobreTiempo()).toString(), tableFont));
        PdfPCell rcHorasCell = new PdfPCell(new Phrase("$"+Numeros.round(rolIndividual.getMontoHorasSuplementarias()).toString(), tableFont));
        PdfPCell bonosCell = new PdfPCell(new Phrase("$"+Numeros.round(rolIndividual.getTotalBonos()).toString(), tableFont));
        PdfPCell subTotalCell = new PdfPCell(new Phrase("$"+Numeros.round(rolIndividual.getSubtotal()).toString(), tableFont));
        PdfPCell decimoTerceroCell = null;
        if (decimo3 != null)
            decimoTerceroCell = new PdfPCell(new Phrase("$"+Numeros.round(decimo3).toString(), tableFont));
        PdfPCell decimoCuartoCell = null;
        if (decimo4 != null)
            decimoCuartoCell = new PdfPCell(new Phrase("$"+Numeros.round(decimo4).toString(), tableFont));

        dias.setPaddingTop(5);
        horasN.setPaddingTop(5);
        horasST.setPaddingTop(5);
        horasRC.setPaddingTop(5);
        salario.setPaddingTop(5);
        stHoras.setPaddingTop(5);
        rcHoras.setPaddingTop(5);
        bonos.setPaddingTop(5);
        subTotal.setPaddingTop(5);
        decimoTercero.setPaddingTop(0);
        decimoCuarto.setPaddingTop(0);

        dias.setHorizontalAlignment(Element.ALIGN_CENTER);
        horasN.setHorizontalAlignment(Element.ALIGN_CENTER);
        horasST.setHorizontalAlignment(Element.ALIGN_CENTER);
        horasRC.setHorizontalAlignment(Element.ALIGN_CENTER);
        salario.setHorizontalAlignment(Element.ALIGN_CENTER);
        stHoras.setHorizontalAlignment(Element.ALIGN_CENTER);
        rcHoras.setHorizontalAlignment(Element.ALIGN_CENTER);
        bonos.setHorizontalAlignment(Element.ALIGN_CENTER);
        subTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
        decimoTercero.setHorizontalAlignment(Element.ALIGN_CENTER);
        decimoCuarto.setHorizontalAlignment(Element.ALIGN_CENTER);

        dias.setBackgroundColor(BaseColor.CYAN);
        horasN.setBackgroundColor(BaseColor.CYAN);
        horasST.setBackgroundColor(BaseColor.CYAN);
        horasRC.setBackgroundColor(BaseColor.CYAN);
        salario.setBackgroundColor(BaseColor.CYAN);
        stHoras.setBackgroundColor(BaseColor.CYAN);
        rcHoras.setBackgroundColor(BaseColor.CYAN);
        bonos.setBackgroundColor(BaseColor.CYAN);
        subTotal.setBackgroundColor(BaseColor.CYAN);
        decimoTercero.setBackgroundColor(BaseColor.CYAN);
        decimoCuarto.setBackgroundColor(BaseColor.CYAN);

        diasCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        horasNCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        horasSTCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        horasRCCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        salarioCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        stHorasCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        rcHorasCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        bonosCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        subTotalCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        if (decimo3 != null)
            decimoTerceroCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        if (decimo4 != null)
            decimoCuartoCell.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(dias);
        table.addCell(horasN);
        table.addCell(horasST);
        table.addCell(horasRC);
        table.addCell(salario);
        table.addCell(stHoras);
        table.addCell(rcHoras);
        table.addCell(bonos);
        table.addCell(subTotal);
        if (decimo3 != null)
            table.addCell(decimoTercero);
        if (decimo4 != null)
            table.addCell(decimoCuarto);

        tableBody.addCell(diasCell);
        tableBody.addCell(horasNCell);
        tableBody.addCell(horasSTCell);
        tableBody.addCell(horasRCCell);
        tableBody.addCell(salarioCell);
        tableBody.addCell(stHorasCell);
        tableBody.addCell(rcHorasCell);
        tableBody.addCell(bonosCell);
        tableBody.addCell(subTotalCell);
        if (decimo3 != null)
            tableBody.addCell(decimoTerceroCell);
        if (decimo4 != null)
            tableBody.addCell(decimoCuartoCell);

        //////////////////////////////////////////////
        float[] columnWith2 = {1f, 1f};

        PdfPTable table2 = new PdfPTable(columnWith2);
        table2.setSpacingBefore(5);
        table2.setWidthPercentage(50);

        PdfPTable tableBody2 = new PdfPTable(columnWith2);
        tableBody2.setSpacingBefore(0);
        tableBody2.setWidthPercentage(50);

        PdfPCell totalCobrado = new PdfPCell(new Phrase("Total Recibido", tableFont));
        PdfPCell totalRetenido = new PdfPCell(new Phrase("Total Agente de Retencion", tableFont));

        totalCobrado.setPaddingTop(5);
        totalRetenido.setPaddingTop(5);
        totalCobrado.setHorizontalAlignment(Element.ALIGN_LEFT);
        totalRetenido.setHorizontalAlignment(Element.ALIGN_LEFT);
        totalCobrado.setBackgroundColor(new BaseColor(144, 238, 144));
        totalRetenido.setBackgroundColor(new BaseColor(205, 92, 92));

        PdfPCell totalcobradoCell = new PdfPCell(new Phrase("$"+Numeros.round(ingreso).toString(), tableFont));
        PdfPCell totalRetenidoCell = new PdfPCell(new Phrase("$"+Numeros.round(totalFinalRetenido).toString(), tableFont));

        totalcobradoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        totalRetenidoCell.setHorizontalAlignment(Element.ALIGN_LEFT);

        table2.addCell(totalCobrado);
        table2.addCell(totalRetenido);

        tableBody2.addCell(totalcobradoCell);
        tableBody2.addCell(totalRetenidoCell);

        try {
            document.add(totales);
            document.add(table);
            document.add(tableBody);
            document.add(table2);
            document.add(tableBody2);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
