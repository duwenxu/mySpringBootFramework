package com.waytogalaxy.display.utils

import com.waytogalaxy.display.tmpc.ReflectUtil
import com.xy.protobuf.DescriptorProtos
import com.xy.protobuf.Descriptors

data class PBSecondsBugReplace(
        var index:Int? = null,
        var proto: DescriptorProtos.FieldDescriptorProto? = null,
        var fullName:String? = null,
        var jsonName:String? = null,
        var file: Descriptors.FileDescriptor? = null,
        var type:Descriptors.FieldDescriptor.Type? = null,
        var containingType:Descriptors.Descriptor? = null,
        var containingOneof:Descriptors.OneofDescriptor? = null,
        var enumType:Descriptors.EnumDescriptor? = null
){
    companion object {
        @JvmStatic fun from(source: Descriptors.FieldDescriptor):PBSecondsBugReplace{
            val dest = PBSecondsBugReplace()
            dest.index = source.index
            dest.proto = ReflectUtil.reflectProp(source,"proto") as DescriptorProtos.FieldDescriptorProto?
            dest.fullName = source.fullName
            dest.jsonName = source.jsonName
            dest.file = source.file
            dest.type = source.type
            dest.containingType = source.containingType
            dest.containingOneof = source.containingOneof
            return dest
        }
    }
}