#Region ;**** Directives created by AutoIt3Wrapper_GUI ****
#AutoIt3Wrapper_Compile_Both=y
#AutoIt3Wrapper_UseX64=y
#EndRegion ;**** Directives created by AutoIt3Wrapper_GUI ****
Sleep(500)
ControlFocus("Open","","Edit1")
Sleep(500)
ControlSetText("Open","","Edit1",$CmdLine[1])
Sleep(500)
ControlClick("Open","","Button1")
Sleep(500)